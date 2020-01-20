package com.epam.esm.controller;

import com.epam.esm.entity.Tag;
import com.epam.esm.repository.specification.SqlSpecification;
import com.epam.esm.repository.specification.factory.SpecificationTagFactory;
import com.epam.esm.repository.specification.tag.TagSpecificationFindAll;
import com.epam.esm.service.TagService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagController {

    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping
    public List<Tag> query(@RequestParam(required = false) String tagName){
        SpecificationTagFactory specificationTagFactory = new SpecificationTagFactory(tagName);
        SqlSpecification sqlSpecification = null;
        if ( tagName == null) {
            sqlSpecification = specificationTagFactory.create("findAllTags");
        } else {
            sqlSpecification = specificationTagFactory.create("findByName");
        }
        return tagService.query(sqlSpecification);
    }

    @PostMapping
    public List<Tag> save(@RequestBody Tag tag) {
        tagService.save(tag);
        return tagService.query(new TagSpecificationFindAll());
    }

    @DeleteMapping("/{id}")
    public List<Tag> delete(@PathVariable int id) {
        tagService.delete(id);
        return tagService.query(new TagSpecificationFindAll());
    }
}
