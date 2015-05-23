package com.example.daniel.tablerow;

/**
 * Created by Daniel on 17/05/2015.
 */
public class Data {

    private String title;
    private String url;
    private String description;
    private  int drawable;

    public Data(String title, String url, String description, int drawable){

        this.drawable=drawable;
        this.url=url;
        this.title=title;
        this.description=description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getDrawable() {
        return drawable;
    }

    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
