package com.epam.esm.repository.specification.certificate;

import com.epam.esm.repository.specification.SqlSpecification;

public class CertificateSpecificationById implements SqlSpecification {
    private int id;

    public CertificateSpecificationById(int id) {
        this.id = id;
    }

    @Override
    public String toSqlClauses() {
        return  " where certificates.id = " + id;
    }
}
