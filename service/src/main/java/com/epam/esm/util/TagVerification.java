package com.epam.esm.util;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.repository.TagRepository;
import org.springframework.stereotype.Service;

@Service
public class TagVerification {

    //    private final TagDao<Tag> tagDao;
    private final TagRepository<Tag> tagRepository;

    public TagVerification(TagRepository<Tag> tagRepository) {
        this.tagRepository = tagRepository;
    }

    public void checkAndSaveTagIfNotExist(GiftCertificate giftCertificate){
//        Tag tag = giftCertificate.getTag();
//        if (!tagRepository.isExistByName(tag.getName())) {
//            tagRepository.save(tag);
//        }
//        tag = tagRepository.query(new TagSpecificationTagByName(tag.getName()));
//        giftCertificate.setTag(tag);
    }
}
