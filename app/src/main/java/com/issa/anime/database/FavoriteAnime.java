package com.issa.anime.database;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "FavAnime")
public class FavoriteAnime {

    @PrimaryKey
    private int id;
    private String imgUrl;
    private String titleNew;
    private String detailanime;

    public FavoriteAnime(int id, String imgUrl, String titleNew, String detailanime) {
        this.id = id;
        this.imgUrl = imgUrl;
        this.titleNew = titleNew;
        this.detailanime = detailanime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
