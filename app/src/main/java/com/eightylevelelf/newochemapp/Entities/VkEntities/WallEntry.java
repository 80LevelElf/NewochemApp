package com.eightylevelelf.newochemapp.Entities.VkEntities;

import java.util.Date;

/**
 * Created by Parents on 14.08.2016.
 */
public abstract class WallEntry {
    private long mVkId;
    private Date mDate;
    private String mDescription;

    public long getVkId() {
        return mVkId;
    }

    public void setVkId(long vkId) {
        mVkId = vkId;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }
}
