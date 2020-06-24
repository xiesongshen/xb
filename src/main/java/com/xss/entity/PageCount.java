package com.xss.entity;

import java.util.List;

/**
 * @author XSS
 * @date 2020/6/24
 * @desc
 */
public class PageCount<T> {
    private Integer count;//总数据
    private Integer pageCount;//总页数
    private Integer page=1;//当前页数
    private Integer size = 5;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    private List<T> data;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getPageCount() {
        pageCount = this.count % this.size == 0 ? this.count / this.size : this.count / this.size + 1;
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
