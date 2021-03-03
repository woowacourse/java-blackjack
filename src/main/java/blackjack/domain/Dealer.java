package blackjack.domain;

public class Dealer extends Participant {
    private static final int STANDARD = 16;

    public Dealer() {
    }

    @Override
    public boolean isDrawable() {
        return calculateTotalValue() <= STANDARD;
    }
}
