package blackjack.view;

import blackjack.domain.card.Denomination;
import java.util.Arrays;

enum OutputDenomination {
    ACE("A"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    TEN("10"),
    JACK("J"),
    QUEEN("Q"),
    KING("K"),
    ;

    private final String value;

    OutputDenomination(String value) {
        this.value = value;
    }

    static String convertDenominationToString(Denomination denomination) {
        return Arrays.stream(OutputDenomination.values())
                .filter(outputDenomination -> outputDenomination.name().equals(denomination.name()))
                .findAny()
                .map(OutputDenomination::getValue)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 숫자입니다."));
    }

    public String getValue() {
        return value;
    }
}
