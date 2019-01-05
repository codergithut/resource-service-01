package com.tianjian;

import com.tianjian.data.bean.core.StaticFileResource;
import com.tianjian.data.bean.relation.RealtionFile;
import com.tianjian.data.service.core.StaticFileResourceDao;
import com.tianjian.data.service.relation.RealtionFileDao;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.UUID;

/**
 * Created by tianjian on 2018/11/29.
 * 测试 hsql 数据库使用
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes=com.tianjian.Application.class)
public class UserDOTest {

    @Autowired
    private StaticFileResourceDao staticFileResourceDao;

    @Autowired
    private RealtionFileDao imageFileDao;

    @Before
    public void before() {
        StaticFileResource staticFileResource = new StaticFileResource();
        staticFileResource.setName("静态资源");
        staticFileResource.setResourceCode(UUID.randomUUID().toString());
        staticFileResource.setSuffix("img");
        staticFileResource.setType("image");

        RealtionFile hotelImageFile = new RealtionFile();
        hotelImageFile.setRelationFileId(UUID.randomUUID().toString());
        hotelImageFile.setResourceCode(UUID.randomUUID().toString());

//        staticFileResourceDao.save(staticFileResource);
//        imageFileDao.save(hotelImageFile);
    }
    @Test
    public void testAdd() {
        List<StaticFileResource> staticFileResources = staticFileResourceDao.findAll();
        System.out.println("===========================================================");
        for(StaticFileResource staticFileResource : staticFileResources) {
            System.out.println(staticFileResource.getName());
        }

        List<RealtionFile> hotelImageFiles = imageFileDao.findAll();
        for(RealtionFile hotelImageFile : hotelImageFiles) {
            System.out.println(hotelImageFile.getRelationFileId());
        }
    }

    @After
    public void after() {
//        userDao.deleteById(1L);
//        userDao.deleteById(3L);
//        userDao.deleteById(5L);
    }

}
