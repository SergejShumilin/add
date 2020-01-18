package com.epam.esm.repository.impl;


import com.epam.esm.entity.Tag;
import com.epam.esm.mapper.TagMapper;
import com.epam.esm.repository.specification.SqlSpecification;
import com.epam.esm.repository.TagRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class TagRepositoryImpl implements TagRepository<Tag> {
    private final static String INSERT = "insert into tags (name) values(?)";
    private final static String DELETE = "DELETE FROM tags WHERE id = ?";
    private final static String GENERAL_QUERY = "select id, name from tags";
    private final static String IS_EXISTS = "SELECT EXISTS(SELECT id FROM tags WHERE id = ?)";
    private final static String IS_EXISTS_BY_NAME = "SELECT EXISTS(SELECT name FROM tags WHERE name = ?)";
    private final JdbcTemplate jdbcTemplate;
    private final TagMapper tagMapper;

    public TagRepositoryImpl(DataSource dataSource, TagMapper tagMapper) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.tagMapper = tagMapper;
    }

    @Override
    public void save(Tag tag) {
        jdbcTemplate.update(INSERT, tag.getName());
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update(DELETE, id);
    }

    @Override
    public List<Tag> query(SqlSpecification specification) {
        String sqlClauses = specification.toSqlClauses();
        return jdbcTemplate.query(GENERAL_QUERY + sqlClauses, tagMapper);
    }

    @Override
    public Boolean isExistById(int id) {
        return jdbcTemplate.queryForObject(IS_EXISTS, Boolean.class, id);
    }

    @Override
    public Boolean isExistByName(String name) {
        return jdbcTemplate.queryForObject(IS_EXISTS_BY_NAME, Boolean.class, name);
    }
}
