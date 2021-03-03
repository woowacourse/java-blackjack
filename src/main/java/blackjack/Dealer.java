package blackjack;

public class Dealer extends Player {
    private static final int MAX_SUM_FOR_MORE_CARD = 16;

    public Dealer(final String name) {
        super(name);
    }

    public boolean checkMoreCardAvailable() {
        return (calculate() < MAX_SUM_FOR_MORE_CARD);
    }
}
