package bleuauction.bleuauction_be.server.notice.controller;

import bleuauction.bleuauction_be.server.member.entity.Member;
import bleuauction.bleuauction_be.server.notice.entity.Notice;
import bleuauction.bleuauction_be.server.notice.entity.NoticeStatus;
import bleuauction.bleuauction_be.server.notice.service.NoticeService;
import bleuauction.bleuauction_be.server.notice.web.NoticeForm;
import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class NoticeController {

  private final NoticeService noticeService;
  private final EntityManager entityManager;

  //등록
  @GetMapping("/api/notice/new")
  public String creatForm(Model model) {
      model.addAttribute("noticeForm", new NoticeForm());
    log.info("notice/new");
    return "/notices/new";
  }

  // 등록 처리
  @PostMapping("/api/notice/new")
  @Transactional
  public String notice(@Valid NoticeForm form) {
    Notice notice = new Notice();
    Member member =entityManager.find(Member.class, 1L);
    notice.setNoticeNo(1L); // 테스트용 1번 회원
    notice.setNoticeTitle(form.getNoticeTitle());
    notice.setNoticeContent(form.getNoticeContent());
    notice.setMember(member);
    notice.setNoticeStatus(NoticeStatus.Y);
    notice = entityManager.merge(notice);
    noticeService.enroll(notice); // 서비스 메소드에서 엔터티 등록
    log.info("notice/postnew");
    return "redirect:/notices";
  }




  //목록조회
  @GetMapping("/api/notices")
  public String list(Model model) {
    List<Notice> notices = noticeService.findNotices();
    model.addAttribute("notices", notices);
    return "notices/noticeList";
  }

  //삭제
  @PostMapping("/api/notices/delete/{noticeNo}")
  public String deleteNotice(@PathVariable("noticeNo") Long noticeNo) {
    noticeService.deleteNotice(noticeNo);
    return "redirect:/notices";
  }

  //수정
  @GetMapping("/api/notice/detail/{noticeNo}")
  public String detailNotice(@PathVariable("noticeNo") Long noticeNo, Model model) {
    Notice notice = noticeService.findOne(noticeNo);
    model.addAttribute("notice", notice);
    return "/notices/detail";
  }

  // 수정 처리
  @PostMapping("/api/notice/update/{noticeNo}")
  public String updateNotice(
          @PathVariable("noticeNo") Long noticeNo,
          @ModelAttribute("notice") @Valid NoticeForm form
  ) {
    noticeService.update(noticeNo, form.getNoticeTitle(), form.getNoticeContent());
    return "redirect:/notices";
  }

}
