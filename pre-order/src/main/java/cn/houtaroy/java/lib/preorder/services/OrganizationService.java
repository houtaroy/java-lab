package cn.houtaroy.java.lib.preorder.services;

import cn.houtaroy.java.lib.preorder.entities.OrganizationEntity;
import cn.houtaroy.java.lib.preorder.repositories.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author Houtaroy
 */
@Component
@RequiredArgsConstructor
public class OrganizationService {
  private final OrganizationRepository repository;

  public List<OrganizationEntity> list(Map<String, Object> params) {
    return repository.find(params);
  }
}
