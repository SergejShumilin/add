package com.epam.esm.service;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Parameters;
import com.epam.esm.exception.CertificateNotFoundException;
import com.epam.esm.repository.impl.CertificationRepositoryImpl;
import com.epam.esm.repository.specification.SqlSpecification;
import com.epam.esm.repository.specification.certificate.CertificateSpecificationById;
import com.epam.esm.repository.specification.factory.SpecificationCertificateFactory;
import com.epam.esm.util.CertificateUpdater;
import com.epam.esm.util.TagVerifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class CertificateService {
    private final CertificationRepositoryImpl certificationRepository;
    private final CertificateUpdater certificateUpdater;
    private final TagVerifier tagVerifier;

    public CertificateService(CertificationRepositoryImpl certificationRepository, CertificateUpdater certificateUpdater, TagVerifier tagVerifier) {
        this.certificationRepository = certificationRepository;
        this.certificateUpdater = certificateUpdater;
        this.tagVerifier = tagVerifier;
    }

    public List<GiftCertificate> query(SqlSpecification certificateSqlSpecification) {
        return certificationRepository.query(certificateSqlSpecification);
    }

    @Transactional
    public void save(GiftCertificate giftCertificate) {
        if (giftCertificate.getTagList() != null) {
            tagVerifier.checkAndSaveTagIfNotExist(giftCertificate);
        }
        String DATE_TIME = ZonedDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        giftCertificate.setCreateDate(DATE_TIME);
        giftCertificate.setLastUpdateDate(DATE_TIME);
        certificationRepository.save(giftCertificate);
    }

    public boolean delete(int id) {
        boolean existById = certificationRepository.existById(id);
        if (!existById) {
            throw new CertificateNotFoundException(id);
        }
        return certificationRepository.delete(id);
    }

    @Transactional
    public void update(GiftCertificate giftCertificate) throws CertificateNotFoundException {
        if (giftCertificate.getTagList() != null) {
            tagVerifier.checkAndSaveTagIfNotExist(giftCertificate);
        }
        boolean existById = certificationRepository.existById(giftCertificate.getId());
        if (!existById) {
            throw new CertificateNotFoundException("name");
        }
        List<GiftCertificate> certificateSet = certificationRepository.query(new CertificateSpecificationById(giftCertificate.getId()));
        GiftCertificate certificateFromDb = certificateSet.stream()
                .findFirst().get();
        boolean equalsCertificates = certificateFromDb.equals(giftCertificate);
        if (!equalsCertificates) {
            certificateUpdater.changeCertificate(certificateFromDb, giftCertificate);
            certificationRepository.update(certificateFromDb);
        }
    }

    public SqlSpecification defineSpecificationByParameters(Parameters parameters) {
        SqlSpecification sqlSpecification = null;
        SpecificationCertificateFactory specificationCertificatesFactory = new SpecificationCertificateFactory(parameters);
        if (parameters.getName() == null && parameters.getDescription() == null && parameters.getTagName() == null) {
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
