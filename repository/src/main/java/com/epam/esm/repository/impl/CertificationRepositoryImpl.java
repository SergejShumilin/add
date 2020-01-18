package com.epam.esm.repository.impl;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.mapper.GiftCertificateMapper;
import com.epam.esm.repository.CertificateRepo;
import com.epam.esm.repository.specification.SqlSpecification;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class CertificationRepositoryImpl implements CertificateRepo<GiftCertificate> {
    private final static String DELETE = "DELETE FROM certificates WHERE id = ?";
    private final static String INSERT = "INSERT INTO certificates (name, description, price, create_date, last_update_date, duration, tag_id) VALUES (?,?,?,?,?,?,?)";
    private final static String UPDATE = "UPDATE certificates SET name = ?, description=?, price=?, create_date=?, last_update_date=?, duration=?, tag_id =? WHERE id = ?";

    private final static String IS_EXISTS_BY_ID = "SELECT EXISTS(SELECT id FROM certificates WHERE id = ?)";
    private final static String GENERAL_QUERY = "select certificates.id, certificates.name, certificates.description, certificates.price, certificates.create_date, certificates.last_update_date, certificates.duration, tags.id as tag_id, tags.name as tag_name from certificates inner join tags on certificates.tag_id=tags.id ";

    private final GiftCertificateMapper giftCertificateMapper;
    private final JdbcTemplate jdbcTemplate;

    public CertificationRepositoryImpl(GiftCertificateMapper giftCertificateMapper, DataSource dataSource) {
        this.giftCertificateMapper = giftCertificateMapper;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<GiftCertificate> query(SqlSpecification specification) {
        String sqlClauses = specification.toSqlClauses();
        return jdbcTemplate.query(GENERAL_QUERY + sqlClauses,giftCertificateMapper);
    }

    @Override
    public void save(GiftCertificate giftCertificate) {
            jdbcTemplate.update(INSERT, giftCertificate.getName(),
                    giftCertificate.getDescription(), giftCertificate.getPrice(),
                    giftCertificate.getCreateDate(), giftCertificate.getLastUpdateDate(),
                    giftCertificate.getDuration(), giftCertificate.getTag().getId());
    }

    @Override
    public void update(GiftCertificate giftCertificate) {
        jdbcTemplate.update(UPDATE, giftCertificate.getName(), giftCertificate.getDescription(),
                giftCertificate.getPrice(), giftCertificate.getCreateDate(), giftCertificate.getLastUpdateDate(),
                giftCertificate.getDuration(), giftCertificate.getTag().getId(), giftCertificate.getId());
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update(DELETE, id);
    }

    @Override
    public Boolean isExistById(int id) {
        return jdbcTemplate.queryForObject(IS_EXISTS_BY_ID, Boolean.class, id);
    }
}
