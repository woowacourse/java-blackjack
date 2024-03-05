package domain;

import java.util.List;

public class Card {

    private final String letter;
    private final String mark;

    public Card(String letter, String mark) {
        this.letter = letter;
        this.mark = mark;
    }

    public int getValue() {
        if ("A".equals(letter)) {
            return 1;
        }
        if (List.of("K", "Q", "J").contains(letter)) {
            return 10;
        }
        return Integer.parseInt(letter);
    }
}
