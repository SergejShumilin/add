package com.epam.esm.repository.specification.factory;

import com.epam.esm.entity.Parameters;
import com.epam.esm.repository.specification.SqlSpecification;
import com.epam.esm.repository.specification.certificate.*;

public class SpecificationCertificateFactory {
    private Parameters parameters;

    public SpecificationCertificateFactory(Parameters parameters) {
        this.parameters = parameters;
    }

    public SqlSpecification create(String action) {
        switch (action) {
            case "findAllCertificates":
                return new CertificateSpecificationFindAll();
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
                throw new IllegalArgumentException("Unknown parameters");
        }
    }
}
