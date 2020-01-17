package com.epam.esm.dao.repository.specification;

import com.epam.esm.dao.repository.CertificateSqlSpecification;

public class CertificateSpecificayionFindAll implements CertificateSqlSpecification {
    @Override
    public String toSqlClauses() {
        return "select certificates.id, certificates.name, certificates.description, certificates.price, certificates.create_date, certificates.last_update_date, certificates.duration, tags.id as tag_id, tags.name as tag_name from certificates inner join tags on certificates.tag_id=tags.id";
    }
}
