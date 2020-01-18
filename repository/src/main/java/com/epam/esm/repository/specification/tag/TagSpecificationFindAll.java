package com.epam.esm.repository.specification.tag;

import com.epam.esm.repository.specification.SqlSpecification;

public class TagSpecificationFindAll implements SqlSpecification {
    private String name;

    public TagSpecificationFindAll(String name) {
        this.name = name;
    }

    @Override
    public String toSqlClauses() {
        return "";
    }
}
