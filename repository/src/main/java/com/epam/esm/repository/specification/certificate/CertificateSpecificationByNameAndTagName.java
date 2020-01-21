package com.epam.esm.repository.specification.certificate;

import com.epam.esm.entity.Parameters;
import com.epam.esm.repository.specification.AbstractSqlSpecification;

public class CertificateSpecificationByNameAndTagName extends AbstractSqlSpecification {
    private Parameters parameters;

    public CertificateSpecificationByNameAndTagName(Parameters parameters) {
        this.parameters = parameters;
    }

    @Override
    public String toSqlClauses() {
        String query = " where certificates.name like '" + "%" + parameters.getName() + "%'"
                + " and tags.name = " + parameters.getTagName() + sort(parameters);
        return query;
    }
}
