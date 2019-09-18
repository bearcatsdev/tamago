package com.bearcats.tamago;

public class Reward_Model {
    int image = 0;
    String imageName ="";

    public Reward_Model(int image, String imageName) {
        this.image = image;
        this.imageName = imageName;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
