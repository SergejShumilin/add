package com.epam.esm.service;

import com.epam.esm.entity.Tag;
import com.epam.esm.exception.TagExistsException;
import com.epam.esm.exception.TagNotFoundException;
import com.epam.esm.repository.TagRepository;
import com.epam.esm.repository.specification.tag.TagSpecificationFindAll;
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
    private TagRepository<Tag> tagRepository;
    @Autowired
    @InjectMocks
    private TagService tagService;

    @Test
    public void testSaveShouldTagDaoCallMethodSave(){
        Tag tag = new Tag();
        tagService.save(tag);
        Mockito.verify(tagRepository, Mockito.times(1)).save(tag);
    }

    @Test
    public void testDeleteShouldTagDaoCallMethodDelete(){
        Mockito.when(tagRepository.existById(1)).thenReturn(true);
        tagService.delete(1);
        Mockito.verify(tagRepository, Mockito.times(1)).delete(1);
    }

    @Test
    public void testFindAllShouldTagDaoCallMethodFindAll(){
        TagSpecificationFindAll tagSpecificationFindAll = new TagSpecificationFindAll();
        tagService.query(tagSpecificationFindAll);
        Mockito.verify(tagRepository, Mockito.times(1)).query(tagSpecificationFindAll);
    }

    @Test(expected = TagExistsException.class)
    public void testSaveShouldThrowException() {
        Tag tag = new Tag();
        tag.setId(1);
        tag.setName("name");
        Mockito.when(tagRepository.existByName("name")).thenReturn(true);
        tagService.save(tag);
    }

    @Test(expected = TagNotFoundException.class)
    public void testDeleteShouldThrowException() {
        Mockito.when(tagRepository.existById(1)).thenReturn(false);
        tagService.delete(1);
    }
}