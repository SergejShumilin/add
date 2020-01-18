package com.epam.esm.repository.specification;

import com.epam.esm.entity.Parameters;
import com.epam.esm.repository.SqlSpecification;

public class CertificateSpecificationSortByName implements SqlSpecification {
    private Parameters parameters;

    public CertificateSpecificationSortByName(Parameters parameters) {
        this.parameters = parameters;
    }

    @Override
    public String toSqlClauses() {
        String query = "ORDER BY certificates.name";
        if (parameters.getTypeSort().equals("desc")){
            query = query + " DESC";
        }
        return query;
    }
}
