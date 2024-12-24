package com.czh.haopicturebackend.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.czh.haopicturebackend.model.dto.picture.PictureQueryRequest;
import com.czh.haopicturebackend.model.dto.picture.PictureReviewRequest;
import com.czh.haopicturebackend.model.dto.picture.PictureUploadRequest;
import com.czh.haopicturebackend.model.entity.Picture;
import com.baomidou.mybatisplus.extension.service.IService;
import com.czh.haopicturebackend.model.entity.User;
import com.czh.haopicturebackend.model.vo.PictureVO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 13762
 * @description 针对表【picture(图片)】的数据库操作Service
 * @createDate 2024-12-20 03:06:28
 */
public interface PictureService extends IService<Picture> {

    void validPicture(Picture picture);

    PictureVO uploadPicture(MultipartFile multipartFile, PictureUploadRequest pictureUploadRequest, User user);

    QueryWrapper<Picture> getQueryWrapper(PictureQueryRequest picureQueryRequest);

    PictureVO getPictureVO(Picture picture, HttpServletRequest request);

    Page<PictureVO> getPictureVOPage(Page<Picture> picturePage, HttpServletRequest request);

    /**
     * 图片审核
     *
     * @param pictureReviewRequest
     * @param loginUser
     */
    void doPictureReview(PictureReviewRequest pictureReviewRequest,User loginUser);

    void fillReviewParams(Picture picture, User loginUser);
}
