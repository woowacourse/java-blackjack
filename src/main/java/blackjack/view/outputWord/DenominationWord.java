package blackjack.view.outputWord;

import blackjack.domain.card.Denomination;
import java.util.Arrays;

public enum DenominationWord {

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
    KING(Denomination.KING, "K"),
    QUEEN(Denomination.QUEEN, "Q"),
    JACK(Denomination.JACK, "J");

    private final Denomination denomination;
    private final String word;

    DenominationWord(Denomination denomination, String word) {
        this.denomination = denomination;
        this.word = word;
    }

    // TODO map으로 정의해서 시간복잡도 낮추기
    public static String toWord(Denomination findDenomination) {
        DenominationWord numberWord = Arrays.stream(values())
                .filter(number -> number.denomination == findDenomination)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당하는 문자가 존재하지 않습니다."));
        return numberWord.word;
    }
}
