package com.example.curd_02.jopo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PetMoreItemVO {

    private Long id;

    private String name;

    private Date dateTimeModified;

    private  Long nums;
}
