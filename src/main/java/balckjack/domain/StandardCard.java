package balckjack.domain;

public class StandardCard extends Card {

    public static final int MAX_BOUND_VALUE = 10;
    public static final int MIN_BOUND_VALUE = 2;

    public StandardCard(Pattern pattern, String symbol) {
        super(pattern, symbol);
    }

    @Override
    protected int getValue() {
        return Integer.parseInt(symbol);
    }
}
