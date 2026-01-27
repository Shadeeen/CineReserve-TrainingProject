package com.example.finalproject.repository;

import com.example.finalproject.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query("""
              SELECT DISTINCT m
              FROM Movie m
              LEFT JOIN m.movieGenres g
              LEFT JOIN m.movieCastMembers c
              WHERE (:fromDate IS NULL OR m.releaseDate >= :fromDate)
              AND (:toDate IS NULL OR m.releaseDate <= :toDate)
              AND (:genres IS NULL OR g.genre.genreName IN :genres )
              AND (:member IS NULL OR c.castMember.CastMemberName LIKE CONCAT('%', :member, '%'))
            """)
    Page<Movie> movieSearch(
            @Param("fromDate") LocalDate fromDate,
            @Param("toDate") LocalDate toDate,
            @Param("genres") List<String> genres,
            @Param("member") String member,
            Pageable pageable
    );


}
