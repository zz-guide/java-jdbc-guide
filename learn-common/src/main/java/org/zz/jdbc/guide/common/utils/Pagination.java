package org.zz.jdbc.guide.common.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pagination<T> {
    private int currentPage;
    private int pageSize;
    private List<T> data;
    private int total;

    public int getTotalPages() {
        return (int) Math.ceil((double) total / pageSize);
    }

    public boolean hasPreviousPage() {
        return currentPage > 1;
    }

    public boolean hasNextPage() {
        return currentPage < getTotalPages();
    }
}
