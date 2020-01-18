package com.epam.esm.repository.specification.tag;

import com.epam.esm.repository.specification.SqlSpecification;

public class TagSpecificationTagByName implements SqlSpecification {
    private String name;

    public TagSpecificationTagByName(String name) {
        this.name = name;
    }

    @Override
    public String toSqlClauses() {
        return String.format(" where tags.name = '%s' ", name);
    }
}
