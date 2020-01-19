package com.epam.esm.service;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.exception.CertificateNotFoundException;
import com.epam.esm.repository.impl.CertificationRepositoryImpl;
import com.epam.esm.repository.specification.SqlSpecification;
import com.epam.esm.util.CertificateUpdater;
import com.epam.esm.util.TagVerification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

@Component
public class CertificateService {
    private final CertificationRepositoryImpl certificationRepository;
    private final CertificateUpdater certificateUpdater;
    private final TagVerification tagVerification;

    public CertificateService(CertificationRepositoryImpl certificationRepository, CertificateUpdater certificateUpdater, TagVerification tagVerification) {
        this.certificationRepository = certificationRepository;
        this.certificateUpdater = certificateUpdater;
        this.tagVerification = tagVerification;
    }

   public Set<GiftCertificate> query(SqlSpecification certificateSqlSpecification){
       return certificationRepository.query(certificateSqlSpecification);
   }

    @Transactional
    public void save(GiftCertificate giftCertificate) {
        if (giftCertificate.getTag()!=null) {
            tagVerification.checkAndSaveTagIfNotExist(giftCertificate);
        }
        String DATE_TIME = ZonedDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        giftCertificate.setCreateDate(DATE_TIME);
        giftCertificate.setLastUpdateDate(DATE_TIME);
        certificationRepository.save(giftCertificate);
    }

    public boolean delete(int id) {
        boolean existById = certificationRepository.existById(id);
        if(!existById){
            throw new CertificateNotFoundException(id);
        }
        return certificationRepository.delete(id);
    }

    @Transactional
    public void update(GiftCertificate giftCertificate) throws CertificateNotFoundException {
        if (giftCertificate.getTag()!=null) {
            tagVerification.checkAndSaveTagIfNotExist(giftCertificate);
        }
        boolean existById = certificationRepository.existById(giftCertificate.getId());
        if (!existById){
            throw new CertificateNotFoundException("name");
        }
//        GiftCertificate certificateFromDb = certificationRepository.findById(giftCertificate.getId());
//        boolean equalsCertificates = certificateFromDb.equals(giftCertificate);
//        if (!equalsCertificates) {
//            certificateUpdater.changeCertificate(certificateFromDb, giftCertificate);
//            certificationRepository.update(certificateFromDb);

    }
}
