package com.epam.esm.repository.impl;


import com.epam.esm.entity.Tag;
import com.epam.esm.mapper.TagMapper;
import com.epam.esm.repository.specification.SqlSpecification;
import com.epam.esm.repository.TagRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class TagRepositoryImpl implements TagRepository<Tag> {
    private final static String INSERT = "INSERT INTO tags (name) VALUES(?)";
    private final static String DELETE = "DELETE FROM tags WHERE id = ?";
    private final static String GENERAL_QUERY = "SELECT id, name FROM tags";
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
    public boolean delete(int id) {
        int delete = jdbcTemplate.update(DELETE, id);
        return delete > 0;
    }

    @Override
    public Set<Tag> query(SqlSpecification specification) {
        String sqlClauses = specification.toSqlClauses();
        List<Tag> tagList = jdbcTemplate.query(GENERAL_QUERY + sqlClauses, tagMapper);
        return new HashSet<>(tagList);
    }

    @Override
    public Boolean existById(int id) {
        return jdbcTemplate.queryForObject(IS_EXISTS, Boolean.class, id);
    }

    @Override
    public Boolean existByName(String name) {
        return jdbcTemplate.queryForObject(IS_EXISTS_BY_NAME, Boolean.class, name);
    }
}
