package com.epam.esm.repository.specification.certificate;

import com.epam.esm.entity.Parameters;
import com.epam.esm.repository.specification.SqlSpecification;

public class CertificateSpecificationByCertificateDescription implements SqlSpecification {
    private Parameters parameters;

    public CertificateSpecificationByCertificateDescription(Parameters parameters) {
        this.parameters = parameters;
    }


    @Override
    public String toSqlClauses() {
        return String.format("where certificates.description like '%s'", "%" + parameters.getDescription() + "%");
    }
}
