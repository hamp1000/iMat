package imat;

import javafx.scene.image.Image;

public class Utils {

    public static Image makeResourceImage(ClassLoader c, String s) {
        return new Image(c.getResourceAsStream("imat/resources/" + s));
    }

}
