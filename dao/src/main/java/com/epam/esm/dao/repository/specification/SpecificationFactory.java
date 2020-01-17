package com.epam.esm.dao.repository.specification;

import com.epam.esm.dao.entity.Parameters;
import com.epam.esm.dao.repository.CertificateSqlSpecification;

public class SpecificationFactory {
    private Parameters parameters;

    public SpecificationFactory(Parameters parameters) {
        this.parameters = parameters;
    }

    public CertificateSqlSpecification create(String action) {
        switch (action) {
            case "byName":
                return new CertificateSpecificationByCertificateName(parameters);
            case "byDescription":
                return new CertificateSpecificationByCertificateDescription(parameters);
            case "byTagName":
                return new CertificateSpecificationByTagName(parameters);
            case "sortByDate":
                return new CertificateSpecificationSortByDate(parameters);
            case "sortByName":
                return new CertificateSpecificationSortByName(parameters);
            default:
                throw new IllegalArgumentException("Unknown command");
        }
    }
}
