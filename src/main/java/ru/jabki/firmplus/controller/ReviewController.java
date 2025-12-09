package ru.jabki.firmplus.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.jabki.firmplus.model.Review;
import ru.jabki.firmplus.service.ReviewService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/review")
@Tag(name = "Отзывы по фильму")
public class ReviewController {
    private ReviewService reviewService;

    @PostMapping
    @Operation(summary = "Отзыв по фильму")
    public void create(@RequestBody final Review review) {
        reviewService.create(review);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить отзывы по фильму")
    public List<Review> getById(@PathVariable("movieId") Long movieId) {
        return reviewService.getById(movieId);
    }

    @DeleteMapping
    @Operation(summary = "Удалить отзыв")
    public void delete(@RequestBody final Review review) {
        reviewService.delete(review);
    }
}
