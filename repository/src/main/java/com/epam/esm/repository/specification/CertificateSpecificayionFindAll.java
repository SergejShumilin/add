package com.epam.esm.repository.specification;

import com.epam.esm.repository.SqlSpecification;

public class CertificateSpecificayionFindAll implements SqlSpecification {
    @Override
    public String toSqlClauses() {
//        return "select certificates.id, certificates.name, certificates.description, certificates.price, certificates.create_date, certificates.last_update_date, certificates.duration, tags.id as tag_id, tags.name as tag_name from certificates inner join tags on certificates.tag_id=tags.id";
        return "";
    }
}
