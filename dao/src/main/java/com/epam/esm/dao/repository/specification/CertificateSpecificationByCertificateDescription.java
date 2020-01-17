package com.epam.esm.dao.repository.specification;

import com.epam.esm.dao.entity.Parameters;
import com.epam.esm.dao.repository.CertificateSqlSpecification;

public class CertificateSpecificationByCertificateDescription implements CertificateSqlSpecification {
    private Parameters parameters;

    public CertificateSpecificationByCertificateDescription(Parameters parameters) {
        this.parameters = parameters;
    }


    @Override
    public String toSqlClauses() {
        return String.format("where certificates.description like '%s'", "%" + parameters.getDescription() + "%");
    }
}
