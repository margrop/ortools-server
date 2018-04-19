package me.ymq.ortools.controller;

import me.ymq.ortools.sample.Sudoku;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class WebController {

    @GetMapping("/hello.do")
    public String hello() {
        return "hello world!";
    }

    @GetMapping("/sudoku.do")
    public String sudoku() {
        Sudoku.solve();
        return "sudoku!";
    }
}
