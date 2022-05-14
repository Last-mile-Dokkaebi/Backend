package com.example.Dokkaebi.domain;

import com.example.Dokkaebi.common.QnaStatus;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
public class Qna {
    @Id @GeneratedValue
    private Long id;
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "member_id")
    @NotNull
    private Member member;
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "admin_id")
    private Member admin;
    private LocalDateTime regiDate;
    private LocalDateTime repliedDate;
    private String content;
    private String comment;
    @Enumerated(EnumType.ORDINAL)
    private QnaStatus status;

    @Builder
    public Qna(Member member, Member admin,LocalDateTime repliedDate, LocalDateTime regiDate, String content, String comment, QnaStatus status){
        this.member=member;
        this.admin=admin;
        this.regiDate=regiDate;
        this.content=content;
        this.comment=comment;
        this.status=status;
        this.repliedDate=repliedDate;
    }
    public void RegiRepliedAdmin(Member admin, String comment){
        this.admin=admin;
        this.comment=comment;
        this.status=QnaStatus.RESPONDED;
        this.repliedDate=LocalDateTime.now();
    }
    public void AdminConfirm(){
        this.status=QnaStatus.UNDERIVIEW;
    }
}
