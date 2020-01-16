package com.epam.esm.dao.impl;


import com.epam.esm.dao.config.DaoConfig;
import com.epam.esm.dao.entity.GiftCertificate;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DaoConfig.class)
@ActiveProfiles("set")
public class GiftCertificateDaoImplTest {
    @Autowired
    private GiftCertificateDaoImpl giftCertificateDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private GiftCertificateMapperTest giftCertificateMapper;

    @Test
    public void findAll() {
        GiftCertificate giftCertificate= giftCertificateDao.findById(2);

        GiftCertificate query = jdbcTemplate.queryForObject("select certificates.id, certificates.name, certificates.description, certificates.price, certificates.create_date, certificates.last_update_date, certificates.duration, tags.id as tag_id, tags.name as tag_name from certificates inner join tags on certificates.tag_id=tags.id where certificates.id = ?", new Object[]{2}, giftCertificateMapper);
        Assert.assertEquals(giftCertificate, query);
    }
}
