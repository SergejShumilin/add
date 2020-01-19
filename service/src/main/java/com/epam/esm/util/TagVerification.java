package com.epam.esm.util;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.repository.TagRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagVerification {

    private final TagRepository<Tag> tagRepository;

    public TagVerification(TagRepository<Tag> tagRepository) {
        this.tagRepository = tagRepository;
    }

    public void checkAndSaveTagIfNotExist(GiftCertificate giftCertificate){
        List<Tag> tagList = giftCertificate.getTag();
        for (Tag tag : tagList) {
            boolean existByName = tagRepository.existByName(tag.getName());
            if (!existByName){
                tagRepository.save(tag);
            }

        }
    }

}