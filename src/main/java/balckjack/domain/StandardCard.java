package balckjack.domain;

class StandardCard extends Card {

    public StandardCard(Pattern pattern, String symbol) {
        super(pattern, symbol);
    }

    @Override
    protected int getValue() {
        return Integer.parseInt(symbol);
    }
}
