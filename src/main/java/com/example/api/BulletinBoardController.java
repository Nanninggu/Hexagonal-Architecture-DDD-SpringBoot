package com.example.api;

import com.example.domain.Post;
import com.example.infrastructure.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BulletinBoardController {

    @Autowired
    private PostMapper postMapper;

    @GetMapping("/bulletin_board")
    public String getBulletinBoard(Model model, @RequestParam(defaultValue = "1") int page) {
        int pageSize = 10;
        int offset = (page - 1) * pageSize;
        List<Post> posts = postMapper.findPostsByPage(offset, pageSize);
        int totalPosts = postMapper.countPosts();
        int totalPages = (int) Math.ceil((double) totalPosts / pageSize);

        model.addAttribute("posts", posts);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        return "bulletin_board";
    }
}
