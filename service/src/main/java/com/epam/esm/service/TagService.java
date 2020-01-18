package com.epam.esm.service;

import com.epam.esm.entity.Tag;
import com.epam.esm.exception.TagExistsException;
import com.epam.esm.exception.TagNotFoundException;
import com.epam.esm.repository.TagRepository;
import com.epam.esm.repository.specification.SqlSpecification;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TagService {
    private final TagRepository<Tag> tagRepository;

    public TagService(TagRepository<Tag> tagRepository) {
        this.tagRepository = tagRepository;
    }

    public List<Tag> query(SqlSpecification specification){
        return tagRepository.query(specification);
    }

    public void save(Tag tag) {
        boolean existByName = tagRepository.isExistByName(tag.getName());
        if (existByName){
            throw new TagExistsException(tag.getName());
        }
        tagRepository.save(tag);
    }

    public void delete(int id) {
        boolean exist = tagRepository.isExistById(id);
        if (!exist){
            throw new TagNotFoundException(id);
        }
        tagRepository.delete(id);
    }
}
