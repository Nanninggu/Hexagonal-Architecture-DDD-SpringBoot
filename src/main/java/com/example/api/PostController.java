package com.example.api;

import com.example.domain.Post;
import com.example.infrastructure.PostMapper;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
public class PostController {

    @Autowired
    private PostMapper postMapper;

    private final Parser parser = Parser.builder().build();
    private final HtmlRenderer renderer = HtmlRenderer.builder().build();

    @GetMapping("/posts")
    public String listPosts(Model model) {
        List<Post> posts = postMapper.findAll();
        for (Post post : posts) {
            String[] lines = post.getContent().split("\n", 2);
            String title = lines[0];
            post.setTitle(title);
        }
        model.addAttribute("posts", posts);
        return "bulletin_board";
    }

    @GetMapping("/editor")
    public String editor() {
        return "editor";
    }

    @PostMapping("/savePost")
    public String savePost(@RequestParam("title") String title, @RequestParam("content") String content) {
        Post post = new Post();
        post.setTitle(title);
        post.setAuthor("Admin");
        post.setDate(LocalDate.now());
        post.setViews(0);
        post.setContent(content);
        postMapper.insert(post);
        return "redirect:/bulletin_board";
    }

    @GetMapping("/post/{id}")
    public String viewPost(@PathVariable("id") Long id, Model model) {
        Post post = postMapper.findById(id);
        String[] lines = post.getContent().split("\n", 2);
        String title = lines[0];
        String content = lines.length > 1 ? lines[1] : "";
        String htmlContent = renderer.render(parser.parse(content));
        post.setTitle(title);
        post.setContent(htmlContent);
        model.addAttribute("post", post);
        return "post";
    }
}