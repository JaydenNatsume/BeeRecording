package com.utarlingtonserc.beerecording.Helper;

import java.io.Serializable;

public class NoteItem implements Serializable {

    private int image;
    private String name;


    public NoteItem() {
        this.image = image;
        this.name = name;
    }

    public NoteItem(int image, String name) {
        this.image = image;
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
