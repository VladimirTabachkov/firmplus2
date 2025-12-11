package ru.jabki.firmplus.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.jabki.firmplus.model.Like;
import ru.jabki.firmplus.service.LikeService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/like")
@Tag(name = "Лайки фильму")
public class LikeController {
    private LikeService likeService;

    @PostMapping
    @Operation(summary = "Лайк фильму")
    public void create(@RequestBody final Like like) {
        likeService.create(like);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить лайки по фильму")
    public List<Like> getById(@PathVariable("movieId") Long movieId) {
        return likeService.getById(movieId);
    }

    @DeleteMapping
    @Operation(summary = "Удалить лайк")
    public void delete(@RequestBody final Like like) {
        likeService.delete(like);
    }
}
