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

//    @Query(value = """
//            SELECT DISTINCT m.*
//            FROM movies m
//            LEFT JOIN movie_genre mg ON m.movie_id = mg.movie_id
//            LEFT JOIN genre g ON g.genre_id = mg.genre_id
//            LEFT JOIN movie_cast_member mc ON m.movie_id = mc.movie_id
//            LEFT JOIN cast_member c ON c.cast_member_id = mc.cast_member_id
//            WHERE (CAST(:fromDate As DATE ) IS NULL OR m.release_date >= :fromDate)
//              AND (CAST(:toDate As DATE) IS NULL OR m.release_date <= :toDate)
//              AND (CAST(:genres AS text[]) IS NULL OR g.genre_name = ANY(CAST(:genres AS text[])))
//              AND (CAST(:member AS text) IS NULL OR LOWER(c.cast_member_name) LIKE LOWER(CONCAT('%', CAST(:member AS text), '%')))
//              AND m.deleted_at IS NULL
//            """,
//            countQuery = """
//                    SELECT COUNT(DISTINCT m.movie_id)
//                    FROM movies m
//                    LEFT JOIN movie_genre mg ON m.movie_id = mg.movie_id
//                    LEFT JOIN genre g ON g.genre_id = mg.genre_id
//                    LEFT JOIN movie_cast_member mc ON m.movie_id = mc.movie_id
//                    LEFT JOIN cast_member c ON c.cast_member_id = mc.cast_member_id
//                    WHERE (CAST(:fromDate AS DATE) IS NULL OR m.release_date >= :fromDate)
//                      AND (CAST(:toDate AS DATE) IS NULL OR m.release_date <= :toDate)
//                      AND (CAST(:genres AS text[]) IS NULL OR g.genre_name = ANY(CAST(:genres AS text[])))
//                      AND (CAST(:member AS text) IS NULL OR LOWER(c.cast_member_name) LIKE LOWER(CONCAT('%', CAST(:member AS text), '%')))
//                      AND m.deleted_at IS NULL
//                    """,
//            nativeQuery = true)

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
