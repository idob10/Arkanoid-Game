package backgrounds;

import biuoop.DrawSurface;
import sprites.Sprite;

import java.awt.Color;

/**
 * @author Ido Barkai
 */
public class Level4Background implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(new Color(57, 163, 239));
        d.fillRectangle(0, 0, 800, 600);
        //First Cloud
        d.setColor(Color.WHITE);
        d.drawLine(55, 400, 25, 600);
        d.drawLine(65, 400, 35, 600);
        d.drawLine(75, 400, 45, 600);
        d.drawLine(85, 400, 55, 600);
        d.drawLine(95, 400, 65, 600);
        d.drawLine(105, 400, 75, 600);
        d.drawLine(115, 400, 85, 600);
        d.setColor(new Color(199, 197, 197));
        d.fillCircle(55, 400, 22);
        d.fillCircle(60, 415, 20);
        d.setColor(new Color(212, 209, 209));
        d.fillCircle(75, 394, 25);
        d.fillCircle(80, 410, 27);
        d.setColor(new Color(177, 176, 176));
        d.fillCircle(105, 410, 30);
        //Second Cloud
        d.setColor(Color.WHITE);
        d.drawLine(475, 490, 500, 600);
        d.drawLine(485, 490, 510, 600);
        d.drawLine(495, 490, 520, 600);
        d.drawLine(505, 490, 530, 600);
        d.drawLine(515, 490, 540, 600);
        d.drawLine(525, 490, 550, 600);
        d.drawLine(535, 490, 560, 600);
        d.setColor(new Color(199, 197, 197));
        d.fillCircle(475, 490, 22);
        d.fillCircle(480, 505, 20);
        d.setColor(new Color(212, 209, 209));
        d.fillCircle(495, 484, 25);
        d.fillCircle(500, 500, 27);
        d.setColor(new Color(177, 176, 176));
        d.fillCircle(525, 500, 30);
    }

    @Override
    public void timePassed() {

    }
}
