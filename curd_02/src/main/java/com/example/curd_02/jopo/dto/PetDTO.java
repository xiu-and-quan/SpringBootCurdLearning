package com.example.curd_02.jopo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.internal.build.AllowPrintStacktrace;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class PetDTO{

    private Integer id;

    // 从前端接收回来的json类型
    private String strDatetimeModified;

    //转成日期类型，用来sql查询
    private LocalDateTime datetimeModified;

    private String name;

}
