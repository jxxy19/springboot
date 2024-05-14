package org.fullstack4.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.fullstack4.dto.BoardDTO;
import org.fullstack4.dto.PageRequestDTO;
import org.fullstack4.dto.PageResponseDTO;
import org.fullstack4.service.BoardServiceIf;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardServiceIf boardService;
//    의존성 주입 , 메서드에서 주입 / 생성자에서 직접 주입
//    메서드 -> 개별 작업 많이 쓸 수 있지만 가급적 사용 X 런타임 에러 생성 가능
    @GetMapping("/list")
    public void list (
            PageRequestDTO pageRequestDTO,
            Model model
    ) {
        PageResponseDTO<BoardDTO> pageResonseDTO = boardService.list(pageRequestDTO);

        log.info("pageResponseDTO : {}", pageResonseDTO);
        model.addAttribute("pageResponseDTO", pageResonseDTO);
    }

    @GetMapping("/view")
    public void view (int idx,
                      PageRequestDTO pageRequestDTO,
                      Model model) {
        log.info("==============================================");
        BoardDTO boardDTO = boardService.view(idx);
        model.addAttribute("dto", boardDTO);
    }

    @RequestMapping(value="/regist", method={RequestMethod.GET})
    public void registGET(Model model) {

    }

    @RequestMapping(value="/regist", method={RequestMethod.POST})
    public String registPOST (
            @Valid BoardDTO boardDTO,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            Model model
    ) {
        log.info("boardController >> registPost Start");
            if(bindingResult.hasErrors()) {
                log.info("BoardController >> registPost Error");
                redirectAttributes.addFlashAttribute("errors",bindingResult.getAllErrors());
                return "redirect:/board/regist";
            }
             int result_idx = boardService.regist(boardDTO);
        redirectAttributes.addFlashAttribute("result_idx",result_idx);
        log.info("boardController >> registPost end");

        return "redirect:/board/list";
    }
}
