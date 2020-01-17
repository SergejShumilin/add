package com.epam.esm.service;

import com.epam.esm.dao.entity.GiftCertificate;
import com.epam.esm.dao.exception.CertificateNotFoundException;
import com.epam.esm.dao.mapper.GiftCertificateMapper;
import com.epam.esm.dao.repository.CertificateSqlSpecification;
import com.epam.esm.dao.repository.impl.CertificationRepositoryImpl;
import com.epam.esm.util.CertificateUpdater;
import com.epam.esm.util.TagVerification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class CertificateService {
    private final CertificationRepositoryImpl certificationRepository;
    private final GiftCertificateMapper giftCertificateMapper;
    private final CertificateUpdater certificateUpdater;
    private final TagVerification tagVerification;

    public CertificateService(CertificationRepositoryImpl certificationRepository, GiftCertificateMapper giftCertificateMapper, CertificateUpdater certificateUpdater, TagVerification tagVerification) {
        this.certificationRepository = certificationRepository;
        this.giftCertificateMapper = giftCertificateMapper;
        this.certificateUpdater = certificateUpdater;
        this.tagVerification = tagVerification;
    }

   public List<GiftCertificate> query(CertificateSqlSpecification certificateSqlSpecification){
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

    public void delete(int id) {
        boolean existById = certificationRepository.isExistById(id);
        if(!existById){
            throw new CertificateNotFoundException(id);
        }
        certificationRepository.delete(id);
    }

    @Transactional
    public void update(GiftCertificate giftCertificate) throws CertificateNotFoundException {
        if (giftCertificate.getTag()!=null) {
            tagVerification.checkAndSaveTagIfNotExist(giftCertificate);
        }
        boolean existById = certificationRepository.isExistById(giftCertificate.getId());
        if (!existById){
            throw new CertificateNotFoundException("name");
        }
//        GiftCertificate certificateFromDb = repocCertiifcate.findById(giftCertificate.getId());
//        boolean equalsCertificates = certificateFromDb.equals(giftCertificate);
//        if (!equalsCertificates) {
//            certificateUpdater.changeCertificate(certificateFromDb, giftCertificate);
//            repocCertiifcate.update(certificateFromDb);

    }

    public List<GiftCertificate> findAll(){
        return certificationRepository.findAll();
    }
}
