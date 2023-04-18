package com.example.week1.controller;

import com.example.week1.dto.BlogRequestDto;
import com.example.week1.dto.BlogResponseDto;
import com.example.week1.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blog")
public class BlogController {

    private final BlogService blogService;

    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @PostMapping("/create")
    public BlogResponseDto createBlog(@RequestBody BlogRequestDto requestDto) {
        return blogService.createBlog(requestDto);
    }

    @GetMapping("/list")
    public List<BlogResponseDto> getBlogList() {
        return blogService.getBlogList();
    }

    @GetMapping("/{id}")
    public BlogResponseDto getBlog(@PathVariable Long id) {
        return blogService.getBlog(id);
    }

    @PutMapping("/update/{id}")
    public BlogResponseDto updateBlog(@PathVariable Long id, @RequestBody BlogRequestDto requestDto) {
        return blogService.updateBlog(id, requestDto);
    }

    @DeleteMapping("/delete/{id}&{password}")
    public String deleteBlog(@PathVariable Long id, @PathVariable String password) {
        return blogService.deleteBlog(id, password);
    }

    @GetMapping("/title/{title}")
    public BlogResponseDto getBlogByTitle(@PathVariable String title) {
        return blogService.getBlogByTitle(title);
    }
}

