package cn.houtaroy.java.lab.preorder.services;

import cn.houtaroy.java.lab.preorder.entities.OrganizationEntity;
import cn.houtaroy.java.lab.preorder.repositories.OrganizationRepository;
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
