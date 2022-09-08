package com.example.curd_02.jopo.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
@Data
public class PetMoreVO {

    private Integer id;

    private String name;

    @JsonFormat(timezone = "GMT+8",pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateTimeModified;

    private Long nums;

    public PetMoreVO(Integer id, String name, LocalDateTime dateTimeModified, Long nums) {
        this.id = id;
        this.name = name;
        this.dateTimeModified = dateTimeModified;
        this.nums = nums;
    }
}
