package observers;
import sprites.Ball;
import sprites.Block;

/**
 * @author Ido Barkai
 */
public class PrintingHitListener implements HitListener {

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("A Block was hit.");
    }
}
