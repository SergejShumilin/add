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
    public void testFindByDescription() {
        List<GiftCertificate> expected = giftCertificateDao.findByDescription("1");
        List<GiftCertificate> actual = jdbcTemplate.query("select certificates.id, certificates.name, certificates.description, certificates.price, certificates.create_date, certificates.last_update_date, certificates.duration, tags.id as tag_id, tags.name as tag_name from certificates inner join tags on certificates.tag_id=tags.id where certificates.description like ?", new Object[]{"1"}, giftCertificateMapper);
        Assert.assertEquals(expected, actual);

    }

    @Test
    public void testFindById() {
        GiftCertificate expected = giftCertificateDao.findById(2);
        GiftCertificate actual = jdbcTemplate.queryForObject("select certificates.id, certificates.name, certificates.description, certificates.price, certificates.create_date, certificates.last_update_date, certificates.duration, tags.id as tag_id, tags.name as tag_name from certificates inner join tags on certificates.tag_id=tags.id where certificates.id = ?", new Object[]{2}, giftCertificateMapper);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testFindByPartName() {
        List<GiftCertificate> expected = giftCertificateDao.findByPartName("1");
        List<GiftCertificate> actual = jdbcTemplate.query("select certificates.id, certificates.name, certificates.description, certificates.price, certificates.create_date, certificates.last_update_date, certificates.duration, tags.id as tag_id, tags.name as tag_name from certificates inner join tags on certificates.tag_id=tags.id where certificates.name like ?", new Object[]{"%" + "1" + "%"}, giftCertificateMapper);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testFindByTag() {
        List<GiftCertificate> expected = giftCertificateDao.findByTag("1");
        List<GiftCertificate> actual = jdbcTemplate.query("select certificates.id, certificates.name, certificates.description, certificates.price, certificates.create_date, certificates.last_update_date, certificates.duration, tags.id as tag_id, tags.name as tag_name from certificates inner join tags on certificates.tag_id=tags.id where tags.name = ?", new Object[]{"1"}, giftCertificateMapper);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testIsExistsById(){
        boolean expected = giftCertificateDao.isExistById(1);
        Boolean actual = jdbcTemplate.queryForObject("SELECT EXISTS(SELECT id FROM certificates WHERE id = ?)", Boolean.class, 1);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testIsExistsByTagName(){
        boolean expected = giftCertificateDao.isExistByTagName("1");
        Boolean actual = jdbcTemplate.queryForObject("SELECT EXISTS(SELECT certificates.name from certificates inner join tags on certificates.tag_id=tags.id where tags.name = ?)", Boolean.class, "1");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testIsExistsByName() {
        boolean expected = giftCertificateDao.isExistByName("1");
        Boolean actual = jdbcTemplate.queryForObject("SELECT EXISTS(SELECT name FROM certificates WHERE name like ?)", Boolean.class, "1");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testIsExistsDescription() {
        Boolean expected = giftCertificateDao.isExistByDescription("1");
        Boolean actual = jdbcTemplate.queryForObject("SELECT EXISTS(SELECT name FROM certificates WHERE description like ?)", Boolean.class, "1");
        Assert.assertEquals(expected, actual);
    }
}
