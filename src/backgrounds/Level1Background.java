package backgrounds;

import biuoop.DrawSurface;
import sprites.Sprite;

import java.awt.Color;

/**
 * @author Ido Barkai
 */
public class Level1Background implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.BLUE);
        d.drawCircle(400, 225, 70);
        d.drawCircle(400, 225, 90);
        d.drawCircle(400, 225, 110);
        d.drawLine(400, 100, 400, 190);
        d.drawLine(400, 260, 400, 350);
        d.drawLine(275, 225, 365, 225);
        d.drawLine(435, 225, 525, 225);
    }

    @Override
    public void timePassed() {

    }
}
