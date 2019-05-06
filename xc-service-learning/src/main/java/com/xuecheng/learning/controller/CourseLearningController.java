package com.xuecheng.learning.controller;

import com.xuecheng.api.learning.CourseLearningControllerApi;
import com.xuecheng.framework.domain.learning.respones.GetMediaResult;
import com.xuecheng.learning.service.LearningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Administrator
 * @version 1.0
 **/
@RestController
@RequestMapping("/learning/course")
public class CourseLearningController implements CourseLearningControllerApi {

    @Autowired
    LearningService learningService;

    @Override
    @GetMapping("/getMedia/{courseId}/{teachplanId}")
    public GetMediaResult getMedia(@PathVariable("courseId") String courseId,
                                   @PathVariable("teachplanId")String teachplanId) {

        return learningService.getmedia(courseId,teachplanId);
    }

    /**
     * 更新课程
     * @param userId
     * @param courseId
     * @return
     */
    @Override
    @PutMapping("/addMedia/{userId}/{courseId}")
    public GetMediaResult addMedia(String userId, String courseId) {
        return null;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @Override
    @GetMapping("/deleteMedia/{id}")
    public GetMediaResult deleteMedia(String id) {
        return null;
    }


}
