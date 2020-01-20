package com.epam.esm.repository.specification.certificate;

import com.epam.esm.entity.Parameters;
import com.epam.esm.repository.specification.SqlSpecification;

public class CertificateSpecificationSortByDate implements SqlSpecification {
    private Parameters parameters;

    public CertificateSpecificationSortByDate(Parameters parameters) {
        this.parameters = parameters;
    }


    @Override
    public String toSqlClauses() {
        String query = "ORDER BY certificates.last_update_date";
        if (parameters.getTypeSort().equalsIgnoreCase("DESC")){
            query = query + " DESC";
        }
        return query;
    }
}
