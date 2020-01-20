package com.epam.esm.util;

import com.epam.esm.entity.GiftCertificate;
import org.springframework.stereotype.Service;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class CertificateUpdater {

    public void changeCertificate(GiftCertificate certificateFromDb, GiftCertificate certificate) {

        if (certificate.getName()!=null && !certificate.getName().equals(certificateFromDb.getName()) ) {
            certificateFromDb.setName(certificate.getName());
        }
        if (certificate.getPrice() != certificateFromDb.getPrice() && certificate.getPrice()!=0) {
            certificateFromDb.setPrice(certificate.getPrice());
        }
        if (certificate.getDescription()!=null && !certificate.getDescription().equals(certificateFromDb.getDescription()) ) {
            certificateFromDb.setDescription(certificate.getDescription());
        }
        if (certificate.getTagList()!=null && !certificate.getTagList().equals(certificateFromDb.getTagList())) {
            certificateFromDb.setTagList(certificate.getTagList());
        }
        if (certificate.getDuration() != certificateFromDb.getDuration()) {
            certificateFromDb.setDuration(certificate.getDuration());
        }
        String dateTime = ZonedDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        certificateFromDb.setLastUpdateDate(dateTime);
    }

}
