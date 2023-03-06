package blackjack.view;

import blackjack.domain.Letter;
import java.util.Arrays;

public enum LetterWord {

    ACE(Letter.ACE, "A"),
    TWO(Letter.TWO, "2"),
    THREE(Letter.THREE, "3"),
    FOUR(Letter.FOUR, "4"),
    FIVE(Letter.FIVE, "5"),
    SIX(Letter.SIX, "6"),
    SEVEN(Letter.SEVEN, "7"),
    EIGHT(Letter.EIGHT, "8"),
    NINE(Letter.NINE, "9"),
    TEN(Letter.TEN, "10"),
    KING(Letter.KING, "K"),
    QUEEN(Letter.QUEEN, "Q"),
    JACK(Letter.JACK, "J");

    private final Letter letter;
    private final String word;

    LetterWord(Letter letter, String word) {
        this.letter = letter;
        this.word = word;
    }

    // TODO map으로 정의해서 시간복잡도 낮추기
    public static String toWord(Letter findLetter) {
        LetterWord numberWord = Arrays.stream(values())
                .filter(number -> number.letter == findLetter)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당하는 문자가 존재하지 않습니다."));
        return numberWord.word;
    }
}
