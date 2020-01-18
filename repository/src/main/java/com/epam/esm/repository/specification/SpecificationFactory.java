package com.epam.esm.repository.specification;

import com.epam.esm.entity.Parameters;
import com.epam.esm.repository.SqlSpecification;

public class SpecificationFactory {
    private Parameters parameters;

    public SpecificationFactory(Parameters parameters) {
        this.parameters = parameters;
    }

    public SqlSpecification create(String action) {
        switch (action) {
            case "findAllCertificates":
                return new CertificateSpecificayionFindAll();
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
                throw new IllegalArgumentException("Unknown specification");
        }
    }
}
