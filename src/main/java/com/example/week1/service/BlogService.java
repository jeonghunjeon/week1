package com.example.week1.service;

import com.example.week1.dto.BlogRequestDto;
import com.example.week1.dto.BlogResponseDto;
import com.example.week1.entity.Blog;
import com.example.week1.entity.Timestamped;
import com.example.week1.repository.BlogRepository;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlogService {

    private final BlogRepository blogRepository;

    @Autowired
    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public BlogResponseDto createBlog(BlogRequestDto requestDto) {
        // 브라우저에서 받아온 데이터를 저장하기 위해서 Course 객체로 변환
        Blog blog = new Blog(requestDto);
        blogRepository.save(blog);
        return new BlogResponseDto(blog);
    }

    public List<BlogResponseDto> getBlogList() {
        // 테이블에 저장되어있는 모든 게시글 목록을 조회
        return blogRepository.findAll().stream().sorted(Comparator.comparing(Timestamped::getCreatedAt).reversed())
                .map(BlogResponseDto::new).collect(Collectors.toList());
    }

    public BlogResponseDto getBlog(Long id) {
        // 조회하기 위해 받아온 Blog 의 id를 사용해서 해당 Blog 인스턴스가 테이블에 존재하는지 확인하고 가져옵니다.
        Blog blog = checkBlog(id);
        return new BlogResponseDto(blog);
    }

    @Transactional
    public BlogResponseDto updateBlog(Long id, BlogRequestDto requestDto) {
        // 수정하기 위해 받아온 Blog 의 id를 사용하여 해당 Blog 인스턴스가 존재하는지 확인하고 가져옵니다.
        // try catch문 사용했었지만 굳이 그럴 필요가 없었음.
        // throw로 Exception을 던지면 해당 위치에서 메서드가 멈추기 때문에 비밀번호가 틀렸을 시 throw해줌.
        // 원래 NullPointerException을 사용했지만 IllegalArgumentException 이 상황에 더 알맞아서 변경.
        // IllegalArgumentException을 catch하지 않고 throw만 할 경우 에러코드가 500이 발생함.
        // 따라서 다른 에러 처리 방법을 고민해야함.
        Blog blog = null;
        blog = checkBlog(id);
        if (blog.getPassword().equals(requestDto.getPassword())) {
            blog.update(requestDto);
        } else {
            throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
        }
        return new BlogResponseDto(blog);
    }

    public String deleteBlog(Long id, String password) {
        // 수정하기 위해 받아온 Blog 의 id를 사용하여 해당 Blog 인스턴스가 존재하는지 확인하고 가져옵니다.
        Blog blog = checkBlog(id);
        if (blog.getPassword().equals(password)) {
            blogRepository.delete(blog);
            return "게시글 삭제에 성공했습니다.";
        } else {
            // 원래 NullPointerException을 사용했지만 IllegalArgumentException 이 상황에 더 알맞아서 변경.
            throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
        }
    }

    public BlogResponseDto getBlogByTitle(String title) {
        Blog blog = blogRepository.findByTitle(title).orElseThrow(
                () -> new IllegalArgumentException("해당하는 제목의 게시글이 없습니다.")
                // 원래 NullPointerException을 사용했지만 IllegalArgumentException 이 상황에 더 알맞아서 변경.
        );
        return new BlogResponseDto(blog);
    }

    private Blog checkBlog(Long id) {
        return blogRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("일치하는 ID가 없습니다.")
                // 원래 NullPointerException을 사용했지만 IllegalArgumentException 이 상황에 더 알맞아서 변경.
        );
    }
}
