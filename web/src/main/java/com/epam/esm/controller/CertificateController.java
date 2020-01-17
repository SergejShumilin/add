package com.epam.esm.controller;

import com.epam.esm.dao.entity.GiftCertificate;
import com.epam.esm.dao.entity.Parameters;
import com.epam.esm.dao.repository.CertificateSqlSpecification;
import com.epam.esm.dao.repository.specification.SpecificationFactory;
import com.epam.esm.service.CertificateService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/certificates")
public class CertificateController {
    private final CertificateService service;

    public CertificateController(CertificateService service) {
        this.service = service;
    }


    @PostMapping(value = "/param")
    public List<GiftCertificate> get(@RequestBody Parameters parameters) {
        CertificateSqlSpecification sqlSpecification = processRequest(parameters);
        return service.query(sqlSpecification);
    }

    @GetMapping
    public List<GiftCertificate> findAll() {
        return service.findAll();
    }

    @PutMapping
    public List<GiftCertificate> update(@RequestBody GiftCertificate giftCertificate) {
        service.update(giftCertificate);
        return service.findAll();
    }

    @PostMapping
    public List<GiftCertificate> save(@RequestBody GiftCertificate giftCertificate) {
        service.save(giftCertificate);
        return service.findAll();
    }

    @DeleteMapping(value = "/{id}")
    public List<GiftCertificate> delete(@PathVariable int id) {
        service.delete(id);
        return service.findAll();
    }

    private CertificateSqlSpecification processRequest(Parameters parameters){
        CertificateSqlSpecification sqlSpecification = null;
        SpecificationFactory specificationFactory = new SpecificationFactory(parameters);
        if (parameters.getName() != null) {
            sqlSpecification = specificationFactory.create("byName");
        } else if (parameters.getDescription() != null) {
            sqlSpecification = specificationFactory.create("byDescription");
        } else if (parameters.getTagName() != null) {
            sqlSpecification = specificationFactory.create("byTagName");
        } else if (parameters.getSort() != null && parameters.getSort().equals("date") && parameters.getTypeSort() != null) {
            sqlSpecification = specificationFactory.create("sortByDate");
        } else if (parameters.getSort() != null && parameters.getSort().equals("name") && parameters.getTypeSort() != null) {
            sqlSpecification = specificationFactory.create("sortByName");
        }
        return sqlSpecification;
    }
}
