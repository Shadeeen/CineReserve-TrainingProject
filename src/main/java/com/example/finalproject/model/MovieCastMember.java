package com.example.finalproject.model;


import jakarta.persistence.*;
import org.hibernate.annotations.Where;

@Entity
@Where(clause = "deleted_at IS NULL")
public class MovieCastMember extends Basic{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long MovieCastMemberId;

    @ManyToOne
    @JoinColumn(name="movie_id")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name="cast_member_id")
    private CastMember castMember;

    public Long getMovieCastMemberId() {
        return MovieCastMemberId;
    }

    public void setMovieCastMemberId(Long movieCastMemberId) {
        MovieCastMemberId = movieCastMemberId;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public CastMember getCastMember() {
        return castMember;
    }

    public void setCastMember(CastMember castMember) {
        this.castMember = castMember;
    }
}
