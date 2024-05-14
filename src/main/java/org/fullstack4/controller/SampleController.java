package org.fullstack4.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Log4j2
@Controller
public class SampleController {
    @GetMapping("/hello")
    public void hello(Model model) {
        model.addAttribute("msg", "HELLO~~");

    }
//    템플릿이 루트
    @GetMapping("/ex/ex1")
    public void ex1(Model model) {
        List<String> list = Arrays.asList("AAA", "bbb", "CCC");
        model.addAttribute("list", list);
    }

    @GetMapping("/ex/ex2")
    public void ex2(Model model) {
        List<String> strList = IntStream.range(1,16)
                .mapToObj(i->"Data"+i)
                .collect(Collectors.toList());
        model.addAttribute("list", strList);

        Map<String, String> map = new HashMap<>();
        map.put("A", "aaaaaa");
        map.put("B", "bbbbbb");
        map.put("C", "cccccc");
        model.addAttribute("map", map);

        SampleDTO dto = new SampleDTO();
        dto.p1 = "Value -- p1 : ";
        dto.p2 = "Value -- p2 : ";
        dto.p3 = "Value -- p3 : ";
        model.addAttribute("dto", dto);
    }
    @GetMapping("layout/fragment")
    public void fragment (Model model) {}
    @GetMapping("ex/ex3")
    public void ex3 (Model model) {}
}


class SampleDTO {
    public String p1;
    public String p2;
    public String p3;
    public String getP1() {
        return p1;
    }

    public String getP2() {
        return p2;
    }

    public String getP3() {
        return p3;
    }



}
