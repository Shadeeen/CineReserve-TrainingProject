package com.example.finalproject.repository;

import com.example.finalproject.model.Screening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ScreeningRepository extends JpaRepository<Screening, Long> {

    @Query(value = """
            SELECT EXISTS (
                SELECT 1
                FROM screening s
                JOIN movies m ON m.movie_id = s.movie_id
                WHERE s.hall_id = :hallId
                  AND s.deleted_at IS NULL
                  AND s.start_time < :end
                  AND (s.start_time + ((m.movie_duration + 30) * INTERVAL '1 minute')) > :newStart
            )
            """, nativeQuery = true)
    boolean existsOverlap(@Param("hallId") Long hallId,
                          @Param("newStart") LocalDateTime newStart,
                          @Param("end") LocalDateTime end);

    @Modifying
    @Query("""
        update Screening s
        set s.deletedAt = :now
        where s.movie.movieId = :movieId
          and s.deletedAt is null
    """)
    int softDeleteByMovieId(@Param("movieId") Long movieId,
                            @Param("now") LocalDateTime now);
}
