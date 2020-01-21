package com.epam.esm.repository.specification;

import com.epam.esm.entity.Parameters;

public abstract class AbstractSqlSpecification implements SqlSpecification {

    public String sort(Parameters parameters){
        String sort = "";
        if (parameters.getSort()!=null && parameters.getTypeSort()!=null) {
            String sortType = "";
            if (parameters.getSort().equalsIgnoreCase("date")) {
                sortType = " ORDER BY certificates.last_update_date";
            } else if (parameters.getSort().equalsIgnoreCase("name")) {
                sortType = " ORDER BY certificates.name";
            }

            if (parameters.getTypeSort().equalsIgnoreCase("DESC")) {
                sort = sortType + " DESC";
            }
        }
        return sort;
    }
}
