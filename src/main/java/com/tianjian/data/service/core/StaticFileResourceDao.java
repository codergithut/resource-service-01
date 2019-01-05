package com.tianjian.data.service.core;

import com.tianjian.data.bean.core.StaticFileResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaticFileResourceDao extends JpaRepository<StaticFileResource, String> {
    List<StaticFileResource> findByResourceCode(String resourceCode);
}
