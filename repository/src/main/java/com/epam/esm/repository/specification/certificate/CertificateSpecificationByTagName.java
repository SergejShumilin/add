package com.epam.esm.repository.specification.certificate;

import com.epam.esm.entity.Parameters;
import com.epam.esm.repository.specification.SqlSpecification;

public class CertificateSpecificationByTagName implements SqlSpecification {
    private Parameters parameters;

    public CertificateSpecificationByTagName(Parameters parameters) {
        this.parameters = parameters;
    }


    @Override
    public String toSqlClauses() {
        return String.format("where tags.name = %s", parameters.getTagName());
    }
}
