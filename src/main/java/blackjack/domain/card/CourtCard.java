package blackjack.domain.card;

import java.util.List;

public class CourtCard extends Card {

    private static final List<String> COURT_VALUES = List.of("J", "Q", "K");
    public static final int COURT_VALUE = 10;

    public CourtCard(Pattern pattern, String value) {
        super(pattern, value);
    }

    @Override
    protected void validateValue(String value) {
        if (!COURT_VALUES.contains(value)) {
            throw new IllegalArgumentException(String.format("심볼은 J, Q, K 중 하나여야 합니다. 입력된 값 : %s", value));
        }
    }

    @Override
    public int getValue() {
        return COURT_VALUE;
    }

}
