package com.example.curd_02.until;

import java.util.Collections;
import java.util.List;

public class PageResult<T> {
        private final Long recordsTotal;
        private final int recordsPages;
        private final List<T> data;

        public PageResult(Long recordsTotal, int recordsPages, List<T> data) {
            this.recordsTotal = recordsTotal;
            this.recordsPages = recordsPages;
            this.data = data;
        }

        public static <T> PageResult<T> empty() {
            return new PageResult<T>(0L, 0, Collections.emptyList());
        }
}
