package com.xuecheng.api.learning;

import com.xuecheng.framework.domain.learning.respones.GetMediaResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Created by Administrator.
 */
@Api(value = "录播课程学习管理",description = "录播课程学习管理")
public interface CourseLearningControllerApi {

    @ApiOperation("获取课程学习地址")
    public GetMediaResult getMedia(String courseId,String teachplanId);

    @ApiOperation("新增课程学习地址")
    public GetMediaResult addMedia(String userId,String courseId);

    @ApiOperation("删除课程学习地址")
    public GetMediaResult deleteMedia(String id);

}
