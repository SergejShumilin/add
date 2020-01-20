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
        String sort = "";
        if (parameters.getSort()!=null && parameters.getTypeSort()!=null) {
            String sortByDate = "";
            if (parameters.getSort().equalsIgnoreCase("date")) {
                sortByDate = " ORDER BY certificates.last_update_date";
            } else if (parameters.getSort().equalsIgnoreCase("name")) {
                sortByDate = " ORDER BY certificates.name";
            }

            if (parameters.getTypeSort().equalsIgnoreCase("DESC")) {
                sortByDate = sortByDate + " DESC";
            }
        }
        return " where certificates.name like '" + "%" + parameters.getName() + "%'" + sort;
    }
}
