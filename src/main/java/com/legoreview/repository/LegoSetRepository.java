package com.legoreview.repository;

import com.legoreview.models.LegoSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LegoSetRepository extends JpaRepository<LegoSet, Long> {
}
