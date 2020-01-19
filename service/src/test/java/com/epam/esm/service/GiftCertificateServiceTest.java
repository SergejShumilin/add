package com.epam.esm.service;


import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.exception.CertificateNotFoundException;
import com.epam.esm.repository.impl.CertificationRepositoryImpl;
import com.epam.esm.repository.specification.certificate.CertificateSpecificationFindAll;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;


@RunWith(MockitoJUnitRunner.class)
public class GiftCertificateServiceTest {

    @Mock
    private CertificationRepositoryImpl certificateRepo;
    @Autowired
    @InjectMocks
    private CertificateService service;

    @Test(expected = CertificateNotFoundException.class)
    public void testDeleteShouldThrowException() {
        Mockito.when(certificateRepo.existById(1)).thenReturn(false);
        service.delete(1);
        Mockito.verify(certificateRepo, Mockito.times(1)).delete(1);
    }

    @Test
    public void testFindAllShouldGiftCertificateDaoCallMethodFindAll() {
        CertificateSpecificationFindAll certificateSpecificationFindAll = new CertificateSpecificationFindAll();
        service.query(certificateSpecificationFindAll);
        Mockito.verify(certificateRepo, Mockito.times(1)).query(certificateSpecificationFindAll);
    }

    @Test
    public void testSaveShouldGiftCertificateDaoCallMethodSave() {
        GiftCertificate giftCertificate = new GiftCertificate();
        service.save(giftCertificate);
        Mockito.verify(certificateRepo, Mockito.times(1)).save(giftCertificate);
    }

    @Test(expected = CertificateNotFoundException.class)
    public void testUpdateShouldThrowException() throws CertificateNotFoundException {
        service.update(new GiftCertificate());
    }
}