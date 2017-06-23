package com.zbwfisher.datasource.connect;

import java.util.List;

/**
 * Created by zbw on 17/4/21.
 */
public class PageModel {

    private List list;
    private int totalRecords;
    private int pageSize;
    private int pageNo;
    private int totalPages;
    private boolean firstPage;
    private boolean lastPage;

    public boolean isFirstPage()
    {
        if (this.pageNo == 1) {
            return true;
        }
        return false;
    }

    public void setFirstPage(boolean firstPage) {
        this.firstPage = firstPage;
    }

    public boolean isLastPage()
    {
        if (this.pageNo == getTotalPages()) {
            return true;
        }
        return false;
    }

    public void setLastPage(boolean lastPage) {
        this.lastPage = lastPage;
    }

    public int getTotalPages()
    {
        if (this.totalRecords == 0) {
            return 1;
        }
        return (this.totalRecords + this.pageSize - 1) / this.pageSize;
    }

    public int getTopPageNo()
    {
        return 1;
    }

    public int getPreviousPageNo()
    {
        if (this.pageNo <= 1) {
            return 1;
        }
        return this.pageNo - 1;
    }

    public int getNextPageNo()
    {
        if (this.pageNo >= getBottomPageNo()) {
            return getBottomPageNo();
        }
        return this.pageNo + 1;
    }

    public int getBottomPageNo()
    {
        return getTotalPages();
    }

    public List getList() {
        return this.list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public int getTotalRecords() {
        return this.totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return this.pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
