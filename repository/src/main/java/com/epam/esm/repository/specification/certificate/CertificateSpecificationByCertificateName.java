package com.epam.esm.repository.specification.certificate;

import com.epam.esm.entity.Parameters;
import com.epam.esm.repository.specification.SqlSpecification;

public class CertificateSpecificationByCertificateName implements SqlSpecification {
    private Parameters parameters;

    public CertificateSpecificationByCertificateName(Parameters parameters) {
        this.parameters = parameters;
    }


    @Override
    public String toSqlClauses() {
        return String.format(" where certificates.name like '%s'", "%" + parameters.getName() + "%");
    }
}
