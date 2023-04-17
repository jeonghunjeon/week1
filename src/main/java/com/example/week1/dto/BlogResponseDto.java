package com.example.week1.dto;

import com.example.week1.entity.Blog;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class BlogResponseDto {
    private Long id;
    private String title;
    private String instructor;
    private String content;
    private LocalDateTime createdAt;

    public BlogResponseDto(Blog blog) {
        this.id = blog.getId();
        this.title = blog.getTitle();
        this.instructor = blog.getInstructor();
        this.content = blog.getContent();
        this.createdAt = blog.getCreatedAt();
    }
}

