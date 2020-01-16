package com.epam.esm.service;


import com.epam.esm.dao.GiftCertificatesDao;
import com.epam.esm.dao.entity.GiftCertificate;
import com.epam.esm.dao.exception.CertificateNotFoundException;

import com.epam.esm.util.CertificateUpdater;
import com.epam.esm.util.TagVerification;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class GiftCertificateService {
    private final GiftCertificatesDao<GiftCertificate> giftCertificatesDao;
    private final CertificateUpdater certificateUpdater;
    private final TagVerification tagVerification;

    public GiftCertificateService(GiftCertificatesDao<GiftCertificate> giftCertificatesDao, CertificateUpdater certificateUpdater, TagVerification tagVerification) {
        this.giftCertificatesDao = giftCertificatesDao;
        this.certificateUpdater = certificateUpdater;
        this.tagVerification = tagVerification;
    }

    public List<GiftCertificate> findAll() {
        return giftCertificatesDao.findAll();
    }

    public List<GiftCertificate> findByPartName(String name)  {
        boolean exist = giftCertificatesDao.isExistByName(name);
        if (!exist) { throw new CertificateNotFoundException(name); }
        return giftCertificatesDao.findByPartName(name);
    }

    public List<GiftCertificate> findByTag(String tagName)  {
        boolean existByTag = giftCertificatesDao.isExistByTagName(tagName);
        if (!existByTag){
            throw new CertificateNotFoundException(tagName);
        }
        return giftCertificatesDao.findByTag(tagName);
    }

    public List<GiftCertificate> findByDescription(String description)  {
        boolean exist = giftCertificatesDao.isExistByDescription(description);
        if (!exist){throw new CertificateNotFoundException(description);}
        return giftCertificatesDao.findByDescription(description);
    }

    public void delete(int id) {
        boolean existById = giftCertificatesDao.isExistById(id);
        if(!existById){
            throw new CertificateNotFoundException(id);
        }
        giftCertificatesDao.delete(id);
    }

    public List<GiftCertificate> sortByDate(String typeSort) {
        return giftCertificatesDao.sortByDate(typeSort);
    }

    public List<GiftCertificate> sortByName(String typeSort) {
        return giftCertificatesDao.sortByName(typeSort);
    }

    public List<GiftCertificate> sortByDateAndName(String typeSort) { return giftCertificatesDao.sortByDateAndName(typeSort); }

    public void save(GiftCertificate giftCertificate) {
        if (giftCertificate.getTag()!=null) {
            tagVerification.checkAndSaveTagIfNotExist(giftCertificate);
        }
        String DATE_TIME = ZonedDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        giftCertificate.setCreateDate(DATE_TIME);
        giftCertificate.setLastUpdateDate(DATE_TIME);
        giftCertificatesDao.save(giftCertificate);
    }

    public void update(GiftCertificate giftCertificate) throws CertificateNotFoundException {
        if (giftCertificate.getTag()!=null) {
            tagVerification.checkAndSaveTagIfNotExist(giftCertificate);
        }

        GiftCertificate certificateFromDb = giftCertificatesDao.findById(giftCertificate.getId());
        boolean equalsCertificates = certificateFromDb.equals(giftCertificate);
        if (!equalsCertificates) {
            certificateUpdater.changeCertificate(certificateFromDb, giftCertificate);
            giftCertificatesDao.update(certificateFromDb);
        }
    }
}
