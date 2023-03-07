package blackjack.view.outputWord;

import blackjack.domain.card.Suit;
import java.util.Arrays;

public enum SuitWord {

    SPADE(Suit.SPADE, "스페이드"),
    HEART(Suit.HEART, "하트"),
    DIAMOND(Suit.DIAMOND, "다이아몬드"),
    CLUB(Suit.CLUB, "클로버");

    private final Suit suit;
    private final String word;

    SuitWord(final Suit suit, final String word) {
        this.suit = suit;
        this.word = word;
    }

    public static String toWord(final Suit findSuit) {
        final SuitWord suitWord = Arrays.stream(values())
                .filter(Suit -> Suit.suit == findSuit)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당하는 슈트이 존재하지 않습니다."));
        return suitWord.word;
    }
}
