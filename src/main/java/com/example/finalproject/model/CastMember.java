package com.example.finalproject.model;


import jakarta.persistence.*;
import org.hibernate.annotations.Where;

@Entity
@Where(clause = "deleted_at IS NULL")
public class CastMember extends Basic{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cast_member_id")
    private Long castMemberId;

    @Column(name = "cast_member_name")
    private String CastMemberName;

    public Long getCastMemberId() {
        return castMemberId;
    }

    public void setCastMemberId(Long castMemberId) {
        this.castMemberId = castMemberId;
    }

    public String getCastMemberName() {
        return CastMemberName;
    }

    public void setCastMemberName(String castMemberName) {
        CastMemberName = castMemberName;
    }
}
