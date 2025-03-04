package domain;

public class DrawPolicy {

    private final int BURST_STANDARD = 21;

    public boolean isDrawable(int numberSum) {
        return numberSum <= BURST_STANDARD;
    }

    public boolean isBurst(int numberSum) {
        return numberSum > BURST_STANDARD;
    }
}
