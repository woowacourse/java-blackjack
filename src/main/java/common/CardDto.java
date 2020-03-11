package common;

import domain.card.Card;

public class CardDto {
    private int value;
    private String word;
    private String pattern;

    private CardDto(int value, String word, String pattern) {
        this.value = value;
        this.word = word;
        this.pattern = pattern;
    }

    public static CardDto of(Card card) {
        return new CardDto(card.getValue(), card.getWord(), card.getPattern());
    }

    public int getValue() {
        return value;
    }

    public String getWord() {
        return word;
    }

    public String getPattern() {
        return pattern;
    }
}
