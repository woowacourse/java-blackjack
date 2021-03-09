package blackjack.domain;

public class Card {
    private final CardPattern pattern;
    private final CardNumber number;

    public Card(CardPattern pattern, CardNumber number) {
        this.pattern = pattern;
        this.number = number;
    }

    public String getPatternAndNumber() {
        return number.getNumber() + pattern.getName();
    }

    public int givePoint() {
        return number.giveNumber();
    }

    public boolean isAce() {
        return number.isAce();
    }
}
