package com.epam.esm.util;

import com.epam.esm.dao.TagDao;
import com.epam.esm.dao.entity.GiftCertificate;
import com.epam.esm.dao.entity.Tag;
import org.springframework.stereotype.Service;

@Service
public class TagVerification {

    private final TagDao<Tag> tagDao;

    public TagVerification(TagDao<Tag> tagDao) {
        this.tagDao = tagDao;
    }

    public void checkAndSaveTagIfNotExist(GiftCertificate giftCertificate){
        Tag tag = giftCertificate.getTag();
        if (!tagDao.isExistByName(tag.getName())) {
            tagDao.save(tag);
        }
        tag = tagDao.findByName(tag.getName());
        giftCertificate.setTag(tag);
    }
}
