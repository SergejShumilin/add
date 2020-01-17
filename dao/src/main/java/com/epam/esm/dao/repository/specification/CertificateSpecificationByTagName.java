package com.epam.esm.dao.repository.specification;

import com.epam.esm.dao.entity.Parameters;
import com.epam.esm.dao.repository.CertificateSqlSpecification;

public class CertificateSpecificationByTagName implements CertificateSqlSpecification {
    private Parameters parameters;

    public CertificateSpecificationByTagName(Parameters parameters) {
        this.parameters = parameters;
    }


    @Override
    public String toSqlClauses() {
        return String.format("where tags.name = %s", parameters.getTagName());
    }
}
