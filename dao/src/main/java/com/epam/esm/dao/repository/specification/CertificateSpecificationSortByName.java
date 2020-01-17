package com.epam.esm.dao.repository.specification;

import com.epam.esm.dao.entity.Parameters;
import com.epam.esm.dao.repository.CertificateSqlSpecification;

public class CertificateSpecificationSortByName implements CertificateSqlSpecification {
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
