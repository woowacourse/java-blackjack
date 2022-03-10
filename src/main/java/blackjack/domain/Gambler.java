package blackjack.domain;

public class Gambler extends Player{
    private static final int GET_CARD_UPPER_BOUND = 20;


    public Gambler(final String name) {
        super(name);
    }

    @Override
    public int getResult() {
        return super.getResult();
    }

    @Override
    public boolean isNotFinished() {
        return getResult() <= GET_CARD_UPPER_BOUND;
    }
}
