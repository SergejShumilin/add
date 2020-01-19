package com.epam.esm.repository.specification.factory;

import com.epam.esm.repository.specification.SqlSpecification;
import com.epam.esm.repository.specification.tag.TagSpecificationFindAll;
import com.epam.esm.repository.specification.tag.TagSpecificationTagByName;

public class SpecificationTagFactory {
    private String name;

    public SpecificationTagFactory(String name) {
        this.name = name;
    }

    public SqlSpecification create(String action) {
        switch (action) {
            case "findAllTags":
                return new TagSpecificationFindAll();
            case "findByName":
                return new TagSpecificationTagByName(name);
            default:
                throw new IllegalArgumentException("Unknown specification");
        }
    }
}
