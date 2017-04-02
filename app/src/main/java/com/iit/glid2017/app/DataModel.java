package com.iit.glid2017.app;

import java.io.Serializable;

/**
 * Created by slim on 07/03/17.
 */

public class DataModel implements Serializable {

    private String mTitle;
    private String mDescription;
    private int mImageRes;
    private boolean mCheck;


    public DataModel() {
        //TODO
    }

    public DataModel(String title, String description, int imageRes, boolean check) {
        mTitle = title;
        mDescription = description;
        mImageRes = imageRes;
        mCheck = check;
    }

    public void reverseCheck() {
        mCheck = !mCheck;
    }

    public boolean isCheck() {
        return mCheck;
    }

    public void setCheck(boolean mCheck) {
        this.mCheck = mCheck;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public int getImageRes() {
        return mImageRes;
    }

    public void setImageRes(int mImageRes) {
        this.mImageRes = mImageRes;
    }
}
