package general;

/**
 * @author Ido Barkai
 */
public class Counter {
    private int count;

    /**
     * Constructor.
     *
     * @param count a number
     */
    public Counter(int count) {
        this.count = count;
    }

    /**
     * add number to current count.
     *
     * @param number a number
     */
    public void increase(int number) {
        count += number;
    }

    /**
     * subtract number from current count.
     *
     * @param number a number
     */
    public void decrease(int number) {
        count -= number;
    }

    /**
     * get current count.
     *
     * @return the count
     */
    public int getValue() {
        return count;
    }
}
