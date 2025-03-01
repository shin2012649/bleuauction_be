package bleuauction.bleuauction_be.server.attach.entity;

import bleuauction.bleuauction_be.server.member.entity.Member;
import bleuauction.bleuauction_be.server.menu.entity.Menu;
import bleuauction.bleuauction_be.server.notice.entity.Notice;
import bleuauction.bleuauction_be.server.review.entity.Review;
import com.fasterxml.jackson.annotation.JsonBackReference;
import bleuauction.bleuauction_be.server.store.entity.Store;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.*;
import org.jetbrains.annotations.NotNull;

import java.sql.Timestamp;

@Entity
@Setter
@Data
@Table(name = "ba_attach")
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
public class Attach {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "file_no")
  private Long fileNo;

  @JsonBackReference
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "menuNo")
  private Menu menuNo;

  @JsonBackReference
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "memberNo")
  private Member memberNo;

  @JsonBackReference
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "reviewNo")
  private Review review;

  @JsonBackReference
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "noticeNo")
  private Notice noticeNo;

  @JsonBackReference
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "storeNo")
  private Store storeNo;

  @NotNull
  private String filePath;

  @NotNull
  private String originFilename;

  @NotNull
  private String saveFilename;

  @CreationTimestamp
  private Timestamp regDatetime;

  @UpdateTimestamp
  private Timestamp mdfDatetime;

  @Enumerated(EnumType.STRING)
  private FileStatus fileStatus;


}
