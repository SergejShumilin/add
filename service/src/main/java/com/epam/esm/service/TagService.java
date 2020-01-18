package com.epam.esm.service;

import com.epam.esm.TagDao;
import com.epam.esm.entity.Tag;
import com.epam.esm.exception.TagExistsException;
import com.epam.esm.exception.TagNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TagService {
    private final TagDao<Tag> tagDao;

    public TagService(TagDao<Tag> tagDao) {
        this.tagDao = tagDao;
    }

    public List<Tag> findAll() {
        return tagDao.findAll();
    }

    public void save(Tag tag) {
        boolean existByName = tagDao.isExistByName(tag.getName());
        if (existByName){
            throw new TagExistsException(tag.getName());
        }
        tagDao.save(tag);
    }

    public void delete(int id) {
        boolean exist = tagDao.isExistById(id);
        if (!exist){
            throw new TagNotFoundException(id);
        }
        tagDao.delete(id);
    }

    public Tag findById(int id){
        boolean exist = tagDao.isExistById(id);
        if (!exist){
            throw new TagNotFoundException(id);
        }
        return tagDao.findById(id);
    }

    public Tag findByName(String name){
        boolean existByName = tagDao.isExistByName(name);
        if (!existByName){
            throw new TagNotFoundException(name);
        }
        return tagDao.findByName(name);
    }
}
