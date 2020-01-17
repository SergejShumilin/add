package com.epam.esm.dao.repository.specification;

import com.epam.esm.dao.entity.Parameters;
import com.epam.esm.dao.repository.CertificateSqlSpecification;

public class CertificateSpecificationSortByDate implements CertificateSqlSpecification {
    private Parameters parameters;

    public CertificateSpecificationSortByDate(Parameters parameters) {
        this.parameters = parameters;
    }


    @Override
    public String toSqlClauses() {
        String query = "ORDER BY certificates.last_update_date";
        if (parameters.getTypeSort().equals("desc")){
            query = query + " DESC";
        }
        return query;
    }
}
