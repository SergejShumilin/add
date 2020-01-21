package com.epam.esm.repository.specification.certificate;

import com.epam.esm.entity.Parameters;
import com.epam.esm.repository.specification.AbstractSqlSpecification;

public class CertificateSpecificationByDescriptionAndTagName extends AbstractSqlSpecification {
    private Parameters parameters;

    public CertificateSpecificationByDescriptionAndTagName(Parameters parameters) {
        this.parameters = parameters;
    }

    @Override
    public String toSqlClauses() {
        String query = " where certificates.description like '" + "%" + parameters.getDescription() + "%'"
                + " and tags.name = " + parameters.getTagName() + sort(parameters);
        return query;
    }
}
