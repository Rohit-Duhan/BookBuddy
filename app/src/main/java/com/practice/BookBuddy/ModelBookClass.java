package com.practice.BookBuddy;

public class ModelBookClass {

    private String mTitle;
    private String mAuthors;
    private String mPublishedDate;
    private String mDescription;
    private String mCategories;
    private String mThumbnail;
    private String mrRetailPrice;
    private String mBuy;
    private String mPreview;
    private String mPrice;
    private int pageCount;
    private String mUrl;

    public ModelBookClass(String mTitle, String mAuthors, String mPublishedDate, String mDescription, String mCategories, String mThumbnail,
             String mBuy, String mPreview, String mPrice, int pageCount, String mUrl) {
        this.mTitle = mTitle;
        this.mAuthors = mAuthors;
        this.mPublishedDate = mPublishedDate;
        this.mDescription = mDescription;
        this.mCategories = mCategories;
        this.mThumbnail = mThumbnail;

        this.mBuy = mBuy;
        this.mPreview = mPreview;
        this.mPrice = mPrice;
        this.pageCount = pageCount;
        this.mUrl = mUrl;
    }

    /**public ModelBookClass(String title, String author, String publishedDate, String description,
                          String categories, String thumbnail, String buy, String previewLink, String price, int pageCount, String url) {
    }*/


    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmAuthors() {
        return mAuthors;
    }

    public void setmAuthors(String mAuthors) {
        this.mAuthors = mAuthors;
    }

    public String getmPublishedDate() {
        return mPublishedDate;
    }

    public void setmPublishedDate(String mPublishedDate) {
        this.mPublishedDate = mPublishedDate;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getmCategories() {
        return mCategories;
    }

    public void setmCategories(String mCategories) {
        this.mCategories = mCategories;
    }

    public String getmThumbnail() {
        return mThumbnail;
    }

    public void setmThumbnail(String mThumbnail) {
        this.mThumbnail = mThumbnail;
    }

    public String getMrRetailPrice() {
        return mrRetailPrice;
    }

    public void setMrRetailPrice(String mrRetailPrice) {
        this.mrRetailPrice = mrRetailPrice;
    }

    public String getmBuy() {
        return mBuy;
    }

    public void setmBuy(String mBuy) {
        this.mBuy = mBuy;
    }

    public String getmPreview() {
        return mPreview;
    }

    public void setmPreview(String mPreview) {
        this.mPreview = mPreview;
    }

    public String getmPrice() {
        return mPrice;
    }

    public void setmPrice(String mPrice) {
        this.mPrice = mPrice;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getmUrl() {
        return mUrl;
    }

    public void setmUrl(String mUrl) {
        this.mUrl = mUrl;
    }
}
