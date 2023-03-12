package view.mapper;

import domain.card.Denomination;

import java.util.Arrays;

public enum CardDenominationMapper {

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

    CardDenominationMapper(Denomination denomination, String value) {
        this.denomination = denomination;
        this.value = value;
    }

    public static String getCardNumber(Denomination denomination) {
        return Arrays.stream(CardDenominationMapper.values())
                .filter(it -> it.denomination == denomination)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 해당하는 카드 타입이 없습니다."))
                .value;
    }
}
