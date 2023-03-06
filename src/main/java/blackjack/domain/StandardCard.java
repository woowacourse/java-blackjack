package blackjack.domain;

class StandardCard extends Card {

    private static final int MIN_INCLUSIVE = 2;
    private static final int MAX_INCLUSIVE = 10;

    public StandardCard(Pattern pattern, String symbol) {
        super(pattern, symbol);
    }

    @Override
    protected void validateSymbol(String symbol) {
        Integer value = Integer.valueOf(symbol);
        if (value < MIN_INCLUSIVE || value > MAX_INCLUSIVE) {
            throw new IllegalArgumentException(String.format("심볼은 2 ~ 10 사이 숫자여야 합니다. 입력된 값 : %d", value));
        }
    }

    @Override
    protected int getValue() {
        return Integer.parseInt(symbol);
    }
}
