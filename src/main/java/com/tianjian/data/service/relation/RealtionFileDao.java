package com.tianjian.data.service.relation;

import com.tianjian.data.bean.relation.RealtionFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RealtionFileDao extends JpaRepository<RealtionFile, String> {
    List<RealtionFile> findByRealtionIdAndResourceCode(String relationId, String resourceCode);
}
