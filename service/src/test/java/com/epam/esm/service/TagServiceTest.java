package com.epam.esm.service;

import com.epam.esm.dao.entity.Tag;
import com.epam.esm.dao.impl.TagDaoImp;
import org.junit.Assert;
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

//    @Test
//    public void testDeleteShouldTagDaoCallMethodDelete(){
//        tagService.delete(1);
//        Mockito.verify(tagDaoImp, Mockito.times(1)).delete(1);
//    }

    @Test
    public void testFindAllShouldTagDaoCallMethodFindAll(){
        tagService.findAll();
        Mockito.verify(tagDaoImp, Mockito.times(1)).findAll();
    }

//    @Test
//    public void testFindByIdShouldTagDaoCallMethodFindById()  {
//        tagService.findById(1);
//        Mockito.verify(tagDaoImp, Mockito.times(1)).findById(1);
//    }

//    @Test
//    public void testFindByNameShouldTagDaoCallMethodFindByName() {
//        tagService.findByName("name");
//        Mockito.verify(tagDaoImp, Mockito.times(1)).findByName("name");
//    }
}