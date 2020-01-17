//package com.epam.esm.service;
//
//import com.epam.esm.dao.entity.GiftCertificate;
//import com.epam.esm.dao.impl.GiftCertificateDaoImpl;
//import com.epam.esm.dao.exception.CertificateNotFoundException;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.beans.factory.annotation.Autowired;
//
//
//@RunWith(MockitoJUnitRunner.class)
//public class GiftCertificateServiceTest {
//
//    @Mock
//    private GiftCertificateDaoImpl giftCertificateDao;
//    @Autowired
//    @InjectMocks
//    private GiftCertificateService service;
//
//    @Test(expected = CertificateNotFoundException.class)
//    public void testDeleteShoulThrowException() {
//        service.delete(1);
//        Mockito.verify(giftCertificateDao, Mockito.times(1)).delete(1);
//    }
//
//    @Test
//    public void testFindAllShouldGiftCertificateDaoCallMethodFindAll() {
//        service.findAll();
//        Mockito.verify(giftCertificateDao, Mockito.times(1)).findAll();
//    }
//
//    @Test(expected = CertificateNotFoundException.class)
//    public void testFindByNameShouldGiftCertificateDaoThrowExceptionWhenCallMethodFindByName() {
//        service.findByPartName("asd");
//        Mockito.verify(giftCertificateDao, Mockito.times(1)).findByPartName("asd");
//    }
//
//    @Test
//    public void testFindByNameShouldGiftCertificateDaoCallMethodFindByName()  {
//        Mockito.when(giftCertificateDao.isExistByName("asd")).thenReturn(true);
//        service.findByPartName("asd");
//        Mockito.verify(giftCertificateDao, Mockito.times(1)).findByPartName("asd");
//    }
//
//    @Test(expected = CertificateNotFoundException.class)
//    public void testFindByDescriptionShouldGiftCertificateDaoCallFindByDescription() {
//        service.findByDescription("description");
//        Mockito.verify(giftCertificateDao, Mockito.times(1)).findByDescription("description");
//    }
//
//    @Test
//    public void testFindByDescriptionShouldGiftCertificateDaoCallMethodFindByDescription() {
//        Mockito.when(giftCertificateDao.isExistByDescription("asd")).thenReturn(true);
//        service.findByDescription("asd");
//        Mockito.verify(giftCertificateDao, Mockito.times(1)).findByDescription("asd");
//    }
//
//    @Test(expected = CertificateNotFoundException.class)
//    public void testFindByDescriptionShouldGiftCertificateDaoCallFindByDate() {
//        service.findByDescription("2020-01-15");
//        Mockito.verify(giftCertificateDao, Mockito.times(1)).findByDescription("2020-01-15");
//    }
//
//    @Test(expected = CertificateNotFoundException.class)
//    public void testFindByTagShouldGiftCertificateDaoCallFindByTag() {
//        service.findByDescription("2020-01-15");
//        Mockito.verify(giftCertificateDao, Mockito.times(1)).findByDescription("2020-01-15");
//    }
//
//    @Test
//    public void testSortByDateShouldGiftCertificateDaoCallMethodSortByDate() {
//        service.sortByDate("asc");
//        Mockito.verify(giftCertificateDao, Mockito.times(1)).sortByDate("asc");
//    }
//
//    @Test
//    public void testSortByNameShouldGiftCertificateDaoCallMethodSortByName() {
//        service.sortByName("name");
//        Mockito.verify(giftCertificateDao, Mockito.times(1)).sortByName("name");
//    }
//
//    @Test
//    public void testSortByDateAndNameShouldGiftCertificateDaoCallMethodSortByDateAndName() {
//        service.sortByDateAndName("asc");
//        Mockito.verify(giftCertificateDao, Mockito.times(1)).sortByDateAndName("asc");
//    }
//
//    @Test
//    public void testSaveShouldGiftCertificateDaoCallMethodSave() {
//        GiftCertificate giftCertificate = new GiftCertificate();
//        service.save(giftCertificate);
//        Mockito.verify(giftCertificateDao, Mockito.times(1)).save(giftCertificate);
//    }
//
//    @Test(expected = CertificateNotFoundException.class)
//    public void testFindByTagShouldReturnException() {
//        service.findByTag("tag");
//    }
//
//    @Test(expected = CertificateNotFoundException.class)
//    public void testUpdateShouldThrowException() throws CertificateNotFoundException {
//        service.update(new GiftCertificate());
//    }
//}