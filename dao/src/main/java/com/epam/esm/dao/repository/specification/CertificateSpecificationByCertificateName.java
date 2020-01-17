package com.epam.esm.dao.repository.specification;

import com.epam.esm.dao.entity.Parameters;
import com.epam.esm.dao.repository.CertificateSqlSpecification;

public class CertificateSpecificationByCertificateName implements CertificateSqlSpecification {
    private Parameters parameters;

    public CertificateSpecificationByCertificateName(Parameters parameters) {
        this.parameters = parameters;
    }


    @Override
    public String toSqlClauses() {
        return String.format("where certificates.name like '%s'", "%" + parameters.getName() + "%");
    }
}
