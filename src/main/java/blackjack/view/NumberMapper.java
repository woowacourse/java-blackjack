package blackjack.view;

import blackjack.domain.card.Denomination;

import java.util.Arrays;

public enum NumberMapper {

    ACE(Denomination.ACE, "A"),
    TWO(Denomination.TWO, "2"),
    THREE(Denomination.THREE, "3"),
    FOUR(Denomination.FOUR, "4"),
    FIVE(Denomination.FIVE, "5"),
    SIX(Denomination.SIX, "6"),
    SEVEN(Denomination.SEVEN, "7"),
    EIGHT(Denomination.EIGHT, "8"),
    NINE(Denomination.NINE, "9"),
    TEN(Denomination.TEN, "10"),
    JACK(Denomination.JACK, "J"),
    QUEEN(Denomination.QUEEN, "Q"),
    KING(Denomination.KING, "K");

    private final Denomination denomination;
    private final String value;

    NumberMapper(Denomination denomination, String value) {
        this.denomination = denomination;
        this.value = value;
    }

    public static String map(Denomination denomination) {
        return Arrays.stream(values())
                .filter(numberMapper -> numberMapper.denomination.equals(denomination))
                .map(NumberMapper::getValue)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("일치하는 숫자가 없습니다."));
    }

    public String getValue() {
        return value;
    }
}
