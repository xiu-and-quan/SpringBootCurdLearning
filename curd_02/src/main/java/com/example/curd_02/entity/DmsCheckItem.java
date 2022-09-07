package com.example.curd_02.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "DMS_CHECK_ITEM")
public class DmsCheckItem{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private String id;

    @Column(name = "ENTERPRISE_ID")
    protected String enterpriseId;
    @Column(name = "ORG_ID")
    protected String orgId;
    @Column(name = "ITEM_CODE")
    protected String itemCode;
    @Column(name = "ITEM_NAME")
    protected String itemName;
    @Column(name = "ITEM_DESC")
    protected String itemDesc;
    @Column(name = "ITEM_TYPE")
    protected String itemType;

}

