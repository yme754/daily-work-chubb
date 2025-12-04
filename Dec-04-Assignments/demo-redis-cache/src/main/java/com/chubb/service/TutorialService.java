package com.chubb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.chubb.model.Tutorial;
import com.chubb.repository.TutorialRepository;

@Service
public class TutorialService {
	@Autowired
    private TutorialRepository repo;

    @Cacheable("tutorials")
    public List<Tutorial> findAll() {
        return repo.findAll();
    }

    public Optional<Tutorial> findById(String id) {
        return repo.findById(id);
    }

    @CacheEvict(value = "tutorials", allEntries = true)
    public Tutorial save(Tutorial tutorial) {
        return repo.save(tutorial);
    }

    @CacheEvict(value = "tutorials", allEntries = true)
    public Optional<Tutorial> update(String id, Tutorial newData) {
        return repo.findById(id).map(t -> {
            t.setTitle(newData.getTitle());
            t.setDescription(newData.getDescription());
            t.setPublished(newData.isPublished());
            return repo.save(t);
        });
    }

    @CacheEvict(value = "tutorials", allEntries = true)
    public void delete(String id) {
        repo.deleteById(id);
    }
}
