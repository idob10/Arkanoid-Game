package observers;

/**
 * @author Ido Barkai
 */
public interface HitNotifier {
    /**
     * Add hl as a listener to hit events.
     * @param hl an instance of hit listener
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hl from the list of listeners to hit events.
     * @param hl hl an instance of hit listener
     */
    void removeHitListener(HitListener hl);
}
