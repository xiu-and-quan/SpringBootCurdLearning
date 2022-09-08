package com.example.curd_02.jopo.dto;

import java.time.LocalDateTime;

/**
 * 未关闭设备查询结果 VO
 * 给JPA用的原生SQL数据接收接口
 * 目标数据不是实体类时的处理方式：定义接口，按照命名规范提供get方法
 */
public interface PetMoreItemDTO {
    Long getId();

    String getName();

    LocalDateTime getDateTimeModified();

    Long getNums();
}
