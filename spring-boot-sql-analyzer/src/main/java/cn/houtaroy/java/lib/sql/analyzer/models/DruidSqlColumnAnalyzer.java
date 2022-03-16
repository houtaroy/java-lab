package cn.houtaroy.java.lib.sql.analyzer.models;

import cn.houtaroy.java.lib.sql.analyzer.repositories.SchemaRepository;
import com.alibaba.druid.DbType;
import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author Houtaroy
 */
@Slf4j
@RequiredArgsConstructor
public class DruidSqlColumnAnalyzer implements SqlColumnAnalyzer {

  private final DbType dbType;
  private final Supplier<DruidSqlAnalyzeVisitor> visitorSupplier;
  private final SchemaRepository schemaRepository;

  @Override
  public List<SimpleColumn> analyze(String sql) {
    SQLStatement sqlStatement = SQLUtils.parseSingleStatement(sql, dbType);
    DruidSqlAnalyzeVisitor visitor = visitorSupplier.get();
    sqlStatement.accept(visitor);
    return analyze(visitor.getSelectColumns(), getColumnDefinitions(visitor.computeSelectTableNames()),
      visitor.computeSelectColumnAliaMap());
  }

  /**
   * 分析查询列结果
   *
   * @param selectColumns SQL解析的查询列列表
   * @param definitions   数据库列定义列表
   * @param aliaMap       查询列别名映射Map
   * @return 查询列分析结果
   */
  private List<SimpleColumn> analyze(List<SimpleColumn> selectColumns, List<? extends ColumnDefinition> definitions,
                                     Map<SimpleColumn, String> aliaMap) {
    List<SimpleColumn> result = new ArrayList<>();
    selectColumns.forEach(column -> {
      if (column.isSelectAll()) {
        addTableColumns(result, column.getTableName(), definitions);
      } else {
        addColumn(result, column, definitions, aliaMap);
      }
    });
    return result;
  }

  /**
   * 根据表名的数据库列定义
   *
   * @param tableNames 表名列表
   * @return 数据库列定义列表
   */
  private List<? extends ColumnDefinition> getColumnDefinitions(Set<String> tableNames) {
    Map<String, Object> parameters = new HashMap<>(1);
    parameters.put("tableNames", tableNames);
    return schemaRepository.listColumnDefinitions(parameters);
  }

  /**
   * 增加表的全部查询列分析结果
   *
   * @param result      已分析的查询列结果
   * @param tableName   表名
   * @param definitions 数据库列定义列表
   */
  private void addTableColumns(List<SimpleColumn> result, String tableName,
                               List<? extends ColumnDefinition> definitions) {
    result.addAll(definitions.stream().filter(definition -> tableName.equals(definition.getTableName()))
      .map(SimpleColumn::new).collect(Collectors.toList()));
  }

  /**
   * 增加查询列分析结果
   *
   * @param result      已分析的查询列结果
   * @param column      SQL解析的查询列
   * @param definitions 数据库列定义列表
   * @param aliaMap     查询列别名映射Map
   */
  private void addColumn(List<SimpleColumn> result, SimpleColumn column, List<? extends ColumnDefinition> definitions,
                         Map<SimpleColumn, String> aliaMap) {
    for (ColumnDefinition definition : definitions) {
      if (column.isDefinition(definition)) {
        SimpleColumn data = new SimpleColumn(definition);
        data.setAlia(aliaMap.get(column));
        result.add(data);
      }
    }
  }
}
