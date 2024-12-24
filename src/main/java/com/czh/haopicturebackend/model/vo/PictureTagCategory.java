package com.czh.haopicturebackend.model.vo;

import lombok.Data;

import java.util.List;

/**
 * 图片标签分类列表视图
 */
@Data
public class PictureTagCategory {

    private List<String> TagList;

    private List<String> CategoryList;
}
