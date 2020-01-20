package com.epam.esm.controller;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Parameters;
import com.epam.esm.repository.specification.SqlSpecification;
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
        SqlSpecification sqlSpecification = service.defineSpecificationByParameters(parameters);
        return service.query(sqlSpecification);
    }

    @PutMapping
    public void update(@RequestBody GiftCertificate giftCertificate) {
        service.update(giftCertificate);
    }

    @PostMapping
    public void save(@RequestBody GiftCertificate giftCertificate) {
        service.save(giftCertificate);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable int id) {
        service.delete(id);
    }


}
