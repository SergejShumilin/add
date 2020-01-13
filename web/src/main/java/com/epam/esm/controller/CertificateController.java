package com.epam.esm.controller;

import com.epam.esm.exception.CertificateNotFoundException;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.dao.entity.GiftCertificate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/certificates")
public class CertificateController {
    private final GiftCertificateService giftCertificateService;

    public CertificateController(GiftCertificateService giftCertificateService) {
        this.giftCertificateService = giftCertificateService;
    }

    @PostMapping
    public List<GiftCertificate> save(@RequestBody GiftCertificate giftCertificate) {
        giftCertificateService.save(giftCertificate);
        return giftCertificateService.getAll();
    }

    @GetMapping
    public List<GiftCertificate> findAll() {
        return giftCertificateService.getAll();
    }
    
    @GetMapping(value = "/{name}")
    public List<GiftCertificate> findByName(@PathVariable String name){
        return giftCertificateService.findByName(name);
    }

    @DeleteMapping(value = "/{id}")
    public List<GiftCertificate> delete(@PathVariable int id) {
        giftCertificateService.delete(id);
        return giftCertificateService.getAll();
    }

    @GetMapping(value = "/sort/{type}")
    public List<GiftCertificate> sort(@PathVariable String type) {
        return giftCertificateService.sortByName(type);
    }

    @PutMapping
    public List<GiftCertificate> update(@RequestBody GiftCertificate giftCertificate) throws CertificateNotFoundException {
        giftCertificateService.update(giftCertificate);
        return giftCertificateService.getAll();
    }

}
