package domain;

public class Card {

    private final CardPattern pattern;
    private CardNumber number;

    public Card(String number, String pattern) {
        this.number = CardNumber.matchCardNumber(number);
        this.pattern = CardPattern.matchCardPattern(pattern);
    }
}
