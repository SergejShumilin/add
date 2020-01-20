package com.epam.esm.repository.impl;

import com.epam.esm.config.DaoConfig;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Parameters;
import com.epam.esm.repository.mapper.GiftCertificateMapperTest;
import com.epam.esm.repository.specification.certificate.CertificateSpecificationByCertificateName;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DaoConfig.class)
@ActiveProfiles("set")
public class CertificationRepositoryImplTest {
    @Autowired
    private CertificationRepositoryImpl repository;
    @Autowired
    @Qualifier("jdbcTest")
    private JdbcTemplate jdbcTemplate;
    @Autowired
    @Qualifier("mapperTest")
    private GiftCertificateMapperTest giftCertificateMapper;

    @Test
    public void existById() {
        boolean expected = repository.existById(1);
        Boolean actual = jdbcTemplate.queryForObject("SELECT EXISTS(SELECT id FROM certificates WHERE id = ?)", Boolean.class, 1);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void query() {
        Parameters parameters =new Parameters();
        parameters.setName("name");
        List<GiftCertificate> expected = repository.query(new CertificateSpecificationByCertificateName(parameters));
        List<GiftCertificate> actual = repository.query(new CertificateSpecificationByCertificateName(parameters));
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void save() {

//        repository.save(new GiftCertificate());
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }

}