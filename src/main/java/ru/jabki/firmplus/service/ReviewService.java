package ru.jabki.firmplus.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.jabki.firmplus.model.Review;
import ru.jabki.firmplus.repository.ReviewRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    @Transactional(rollbackFor = Exception.class)
    public Review create(final Review review) {
        return reviewRepository.insert(review);
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(final Review review) {
        reviewRepository.delete(review);
    }

    @Transactional(readOnly = true)
    public List<Review> getById(final long movieId) {
        return reviewRepository.findAll(movieId);
    }
}
