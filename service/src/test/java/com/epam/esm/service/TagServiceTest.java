package com.epam.esm.service;

import com.epam.esm.entity.Tag;
import com.epam.esm.exception.TagExistsException;
import com.epam.esm.exception.TagNotFoundException;
import com.epam.esm.impl.TagDaoImp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;


@RunWith(MockitoJUnitRunner.class)
public class TagServiceTest {
    @Mock
    private TagDaoImp tagDaoImp;
    @Autowired
    @InjectMocks
    private TagService tagService;

    @Test
    public void testSaveShouldTagDaoCallMethodSave(){
        Tag tag = new Tag();
        tagService.save(tag);
        Mockito.verify(tagDaoImp, Mockito.times(1)).save(tag);
    }

    @Test
    public void testDeleteShouldTagDaoCallMethodDelete(){
        Mockito.when(tagDaoImp.isExistById(1)).thenReturn(true);
        tagService.delete(1);
        Mockito.verify(tagDaoImp, Mockito.times(1)).delete(1);
    }

    @Test
    public void testFindAllShouldTagDaoCallMethodFindAll(){
        tagService.findAll();
        Mockito.verify(tagDaoImp, Mockito.times(1)).findAll();
    }

    @Test(expected = TagNotFoundException.class)
    public void testFindByIdShouldThrowException()  {
        tagService.findById(1);
        Mockito.verify(tagDaoImp, Mockito.times(1)).findById(1);
    }

    @Test(expected = TagNotFoundException.class)
    public void testFindByNameShouldThrowException() {
        tagService.findByName("name");
        Mockito.verify(tagDaoImp, Mockito.times(1)).findByName("name");
    }

    @Test(expected = TagExistsException.class)
    public void testSaveShouldThrowException() {
        Tag tag = new Tag();
        tag.setId(1);
        tag.setName("name");
        Mockito.when(tagDaoImp.isExistByName("name")).thenReturn(true);
        tagService.save(tag);
    }

    @Test(expected = TagNotFoundException.class)
    public void testDeleteShouldThrowException() {
        Mockito.when(tagDaoImp.isExistById(1)).thenReturn(false);
        tagService.delete(1);
    }

    @Test
    public void testFindByIdShouldTagDaoCallMethodFindById() {
        Mockito.when(tagDaoImp.isExistById(1)).thenReturn(true);
        tagService.findById(1);
        Mockito.verify(tagDaoImp, Mockito.times(1)).findById(1);
    }

    @Test
    public void tetsFindByNameShouldTagDaoCallMethodFindByName() {
        Mockito.when(tagDaoImp.isExistByName("name")).thenReturn(true);
        tagService.findByName("name");
        Mockito.verify(tagDaoImp, Mockito.times(1)).findByName("name");
    }
}