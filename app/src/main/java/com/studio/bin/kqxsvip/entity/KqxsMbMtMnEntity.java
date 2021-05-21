package com.studio.bin.kqxsvip.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class KqxsMbMtMnEntity {
    @SerializedName("data")
    @Expose
    private List<List<DataElement>> data = null;
    @SerializedName("count")
    @Expose
    private Integer count;

    public List<List<DataElement>> getData() {
        return data;
    }

    public void setData(List<List<DataElement>> data) {
        this.data = data;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
