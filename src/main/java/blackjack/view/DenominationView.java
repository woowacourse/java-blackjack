package blackjack.view;

import java.util.Arrays;
import java.util.NoSuchElementException;

import blackjack.domain.card.Denomination;

public enum DenominationView {

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

    public static final String NO_SUCH_DENOMINATION = "일치하는 denomination이 없습니다.";

    private final Denomination denomination;
    private final String name;

    DenominationView(Denomination denomination, String name) {
        this.denomination = denomination;
        this.name = name;
    }

    public static String getName(Denomination denomination) {
        return Arrays.stream(values())
            .filter(d -> denomination == d.getDenomination())
            .findAny()
            .orElseThrow(() -> new NoSuchElementException(NO_SUCH_DENOMINATION))
            .name;
    }

    public Denomination getDenomination() {
        return denomination;
    }
}
