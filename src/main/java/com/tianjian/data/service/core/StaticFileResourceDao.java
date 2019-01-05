package com.tianjian.data.service.core;

import com.tianjian.data.bean.core.StaticFileResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaticFileResourceDao extends JpaRepository<StaticFileResource, String> {
}
