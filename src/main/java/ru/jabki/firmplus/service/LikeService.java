package ru.jabki.firmplus.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.jabki.firmplus.model.Like;
import ru.jabki.firmplus.repository.LikeRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;

    @Transactional(rollbackFor = Exception.class)
    public Like create(final Like like) {
        return likeRepository.insert(like);
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(final Like like) {
        likeRepository.delete(like);
    }

    @Transactional(readOnly = true)
    public List<Like> getById(final long userId) {
        return likeRepository.findAll(userId);
    }
}
