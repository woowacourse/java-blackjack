package blackjack.domain.card;

public class StandardCard extends Card {

    private static final int MIN_INCLUSIVE = 2;
    private static final int MAX_INCLUSIVE = 10;

    public StandardCard(Pattern pattern, String value) {
        super(pattern, value);
    }

    @Override
    protected void validateValue(String inputValue) {
        Integer value = Integer.valueOf(inputValue);
        if (value < MIN_INCLUSIVE || value > MAX_INCLUSIVE) {
            throw new IllegalArgumentException(String.format("심볼은 2 ~ 10 사이 숫자여야 합니다. 입력된 값 : %d", value));
        }
    }

    @Override
    public int getValue() {
        return Integer.parseInt(value);
    }
}
