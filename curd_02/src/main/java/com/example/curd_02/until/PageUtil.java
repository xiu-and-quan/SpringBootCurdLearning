package com.example.curd_02.until;

import org.springframework.data.domain.PageRequest;

public class PageUtil {
    public static PageRequest of(int pageIndex, int pageSize) {
        return PageRequest.of(pageIndex - 1, pageSize);
    }
}