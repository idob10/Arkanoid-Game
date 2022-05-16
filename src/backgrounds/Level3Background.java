package backgrounds;

import biuoop.DrawSurface;
import sprites.Sprite;

import java.awt.Color;

/**
 * @author Ido Barkai
 */
public class Level3Background implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(new Color(51, 153, 51));
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(new Color(31, 31, 30));
        d.fillRectangle(35, 450, 90, 600);
        d.setColor(new Color(63, 62, 57));
        d.fillRectangle(60, 375, 40, 75);
        d.setColor(new Color(76, 75, 67));
        d.fillRectangle(75, 150, 10, 225);
        d.setColor(Color.ORANGE);
        d.fillCircle(80, 145, 15);
        d.setColor(Color.red.brighter());
        d.fillCircle(80, 145, 10);
        d.setColor(Color.WHITE);
        d.fillCircle(80, 145, 5);
        d.setColor(Color.WHITE.brighter());
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                d.fillRectangle(45 + i * 15, 460 + 32 * j, 10, 20);
            }
        }
    }

    @Override
    public void timePassed() {

    }
}
