package backgrounds;

import biuoop.DrawSurface;
import sprites.Sprite;

import java.awt.Color;

/**
 * @author Ido Barkai
 */
public class Level2Background implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(new Color(51, 102, 255));
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(new Color(255, 224, 102));
        for (int i = 0; i < 160; i++) {
            d.drawLine(40, 200, 5 * i, 300);
        }
        d.fillCircle(50, 200, 40);
        d.setColor(new Color(255, 255, 179));
        d.fillCircle(50, 200, 30);
        d.setColor(Color.yellow);
        d.fillCircle(50, 200, 20);

    }

    @Override
    public void timePassed() {

    }
}
