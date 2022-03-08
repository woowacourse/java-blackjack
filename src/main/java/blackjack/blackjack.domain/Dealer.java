package blackjack.blackjack.domain;

public class Dealer extends Player {
    private static final int MORE_CARD_CRITERIA = 16;

    public Dealer(final String name) {
        super(name);
    }

    public boolean isUnderSixteen() {
        return super.getResult() <= MORE_CARD_CRITERIA;
    }
}
