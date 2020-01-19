package com.epam.esm.repository.impl;

import com.epam.esm.entity.AbstractEntity;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.mapper.GiftCertificateMapper;
import com.epam.esm.mapper.TagMapper;
import com.epam.esm.repository.CertificateRepo;
import com.epam.esm.repository.specification.SqlSpecification;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CertificationRepositoryImpl implements CertificateRepo<GiftCertificate> {
    private final static String INSERT = "INSERT INTO certificates (name, description, price, create_date, " +
                                         "last_update_date, duration) VALUES (?,?,?,?,?,?)";
    private final static String INSERT_CONNECT = "INSERT INTO connecting (certificate_id, tag_id) VALUES (?,?)";
    private final static String UPDATE = "UPDATE certificates SET name = ?, description=?, price=?, create_date=?,"
                                          + " last_update_date=?, duration=?, tag_id =? WHERE id = ?";
    private final static String DELETE = "DELETE FROM certificates WHERE id = ?";
    private final static String FIND_CERTIFICATES_ID = "SELECT id FROM certificates WHERE name = ?";
    private final static String FIND_TAG_ID = "SELECT id FROM tags WHERE name = ?";
    private final static String IS_EXISTS_BY_ID = "SELECT EXISTS(SELECT id FROM certificates WHERE id = ?)";
    private final static String GENERAL_QUERY = "SELECT certificates.id, certificates.name, certificates.description,"
                                              + "certificates.price, certificates.create_date,"
                                              + " certificates.last_update_date, certificates.duration FROM connecting INNER JOIN certificates "
                                              + "ON connecting.certificate_id=certificates.id INNER JOIN tags ON connecting.tag_id=tags.id ";
    private final static String FIND_TAG_BY_CERTIFICATE_ID = "SELECT tags.id, tags.name FROM connecting INNER JOIN certificates "
                                                           + " ON connecting.certificate_id=certificates.id INNER JOIN tags ON "
                                                           + "connecting.tag_id=tags.id WHERE connecting.certificate_id = ?";

    private final GiftCertificateMapper giftCertificateMapper;
    private final JdbcTemplate jdbcTemplate;

    public CertificationRepositoryImpl(GiftCertificateMapper giftCertificateMapper, DataSource dataSource) {
        this.giftCertificateMapper = giftCertificateMapper;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Set<GiftCertificate> query(SqlSpecification specification) {
        String sqlClauses = specification.toSqlClauses();
        String query = GENERAL_QUERY + sqlClauses;
        List<GiftCertificate> certificateList = jdbcTemplate.query(query, giftCertificateMapper);
        for (GiftCertificate cert : certificateList) {
            List<Tag> tagList = jdbcTemplate.query(FIND_TAG_BY_CERTIFICATE_ID, new Object[]{cert.getId()}, new TagMapper());
            cert.setTag(tagList);
        }
        return new HashSet<>(certificateList);
    }

    @Override
    public void save(GiftCertificate giftCertificate) {
        jdbcTemplate.update(INSERT, giftCertificate.getName(),
                giftCertificate.getDescription(), giftCertificate.getPrice(),
                giftCertificate.getCreateDate(), giftCertificate.getLastUpdateDate(),
                giftCertificate.getDuration());
        saveConnect(giftCertificate);
    }

    private void saveConnect(GiftCertificate giftCertificate) {
        int certificateId = jdbcTemplate.queryForObject(FIND_CERTIFICATES_ID, new Object[]{giftCertificate.getName()}, Integer.class);

        List<String> tagName = giftCertificate.getTag().stream()
                .map(AbstractEntity::getName)
                .collect(Collectors.toList());
        for (String name : tagName) {
            int tagId = jdbcTemplate.queryForObject(FIND_TAG_ID, new Object[]{name}, Integer.class);
            jdbcTemplate.update(INSERT_CONNECT, certificateId, tagId);
        }
    }

    @Override
    public void update(GiftCertificate giftCertificate) {
//        jdbcTemplate.update(UPDATE, giftCertificate.getName(), giftCertificate.getDescription(),
//                giftCertificate.getPrice(), giftCertificate.getCreateDate(), giftCertificate.getLastUpdateDate(),
//                giftCertificate.getDuration(), giftCertificate.getTag().getId(), giftCertificate.getId());
    }

    @Override
    public boolean delete(int id) {
        int delete = jdbcTemplate.update(DELETE, id);
        return delete > 0;
    }


    @Override
    public Boolean existById(int id) {
        return jdbcTemplate.queryForObject(IS_EXISTS_BY_ID, Boolean.class, id);
    }
}
