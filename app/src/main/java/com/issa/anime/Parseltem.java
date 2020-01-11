package com.issa.anime;

import android.os.Parcel;
import android.os.Parcelable;

public class Parseltem implements Parcelable {
    private String imgUrl;
    private String titleNew;
    private String detailanime;

    public Parseltem(String imgUrl, String titleNew, String detailanime) {
        this.imgUrl = imgUrl;
        this.titleNew = titleNew;
        this.detailanime = detailanime;

    }

    protected Parseltem(Parcel in) {
        imgUrl = in.readString();
        titleNew = in.readString();
        detailanime = in.readString();

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(imgUrl);
        dest.writeString(titleNew);
        dest.writeString(detailanime);

    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Parseltem> CREATOR = new Creator<Parseltem>() {
        @Override
        public Parseltem createFromParcel(Parcel in) {
            return new Parseltem(in);
        }

        @Override
        public Parseltem[] newArray(int size) {
            return new Parseltem[size];
        }
    };

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTitleNew() {
        return titleNew;
    }

    public void setTitleNew(String titleNew) {
        this.titleNew = titleNew;
    }

    public String getDetailanime() {
        return detailanime;
    }

    public void setDetailanime(String detailanime) {
        this.detailanime = detailanime;
    }

}