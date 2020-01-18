package com.epam.esm.controller;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Parameters;
import com.epam.esm.repository.SqlSpecification;
import com.epam.esm.repository.specification.CertificateSpecificayionFindAll;
import com.epam.esm.repository.specification.SpecificationFactory;
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

    @GetMapping
    public List<GiftCertificate> query(@RequestParam(required = false) String name, @RequestParam(required = false) String description,
                                       @RequestParam(required = false) String tagName, @RequestParam(required = false) String sort,
                                       @RequestParam(required = false) String typeSort) {
        Parameters parameters = new Parameters(name, description, tagName, sort, typeSort);
        SqlSpecification sqlSpecification = processRequest(parameters);
        return service.query(sqlSpecification);
    }

    @PutMapping
    public List<GiftCertificate> update(@RequestBody GiftCertificate giftCertificate) {
        service.update(giftCertificate);
        return service.query(new CertificateSpecificayionFindAll());
    }

    @PostMapping
    public List<GiftCertificate> save(@RequestBody GiftCertificate giftCertificate) {
        service.save(giftCertificate);
        return service.query(new CertificateSpecificayionFindAll());
    }

    @DeleteMapping(value = "/{id}")
    public List<GiftCertificate> delete(@PathVariable int id) {
        service.delete(id);
        return service.query(new CertificateSpecificayionFindAll());
    }

    private SqlSpecification processRequest(Parameters parameters){
        SqlSpecification sqlSpecification = null;
        SpecificationFactory specificationCertificatesFactory = new SpecificationFactory(parameters);
        if ( parameters.getName() == null && parameters.getDescription() == null && parameters.getTagName() == null &&
                parameters.getSort() == null && parameters.getTypeSort() == null) {
            sqlSpecification = specificationCertificatesFactory.create("findAllCertificates");
        } else {
            if (parameters.getName() != null) {
                sqlSpecification = specificationCertificatesFactory.create("byName");
            } else if (parameters.getDescription() != null) {
                sqlSpecification = specificationCertificatesFactory.create("byDescription");
            } else if (parameters.getTagName() != null) {
                sqlSpecification = specificationCertificatesFactory.create("byTagName");
            } else if (parameters.getSort() != null && parameters.getSort().equals("date") && parameters.getTypeSort() != null) {
                sqlSpecification = specificationCertificatesFactory.create("sortByDate");
            } else if (parameters.getSort() != null && parameters.getSort().equals("name") && parameters.getTypeSort() != null) {
                sqlSpecification = specificationCertificatesFactory.create("sortByName");
            }
        }
        return sqlSpecification;
    }
}
