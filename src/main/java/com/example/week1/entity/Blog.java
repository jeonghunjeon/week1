package com.example.week1.entity;

import com.example.week1.dto.BlogRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Entity
@NoArgsConstructor
public class Blog extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String instructor;
    private String password;
    private String content;

    public Blog(BlogRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.instructor = requestDto.getInstructor();
        this.password = requestDto.getPassword();
        this.content = requestDto.getContent();
    }

    public void update(BlogRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.instructor = requestDto.getInstructor();
        this.password = requestDto.getPassword();
        this.content = requestDto.getContent();
    }
}

