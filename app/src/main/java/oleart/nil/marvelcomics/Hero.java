package oleart.nil.marvelcomics;

import android.graphics.Bitmap;

/**
 * Created by niloleart on 3/12/16.
 */

public class Hero {


    public Hero(String name, String description, String image, String id) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public String getId() {
        return id;
    }

    public void setImage(String image) {
        this.image = image;
    }

    private String name, description;
    private String image;
    private String id;
}
