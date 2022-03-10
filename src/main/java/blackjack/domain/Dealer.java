package blackjack.domain;

public class Dealer extends Player {
    private static final int GET_CARD_UPPER_BOUND = 16;

    public Dealer(final String name) {
        super(name);
    }


    @Override
    public boolean isNotFinished() {
        return getResult() <= GET_CARD_UPPER_BOUND;
    }


}
