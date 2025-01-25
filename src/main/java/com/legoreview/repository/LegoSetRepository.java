package com.legoreview.repository;

import com.legoreview.models.LegoSet;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface LegoSetRepository extends JpaRepository<LegoSet, Long> {
    List<LegoSet> findByTheme(String theme);

    List<LegoSet> findByReleaseDateAfter(LocalDate releaseDate);
}
