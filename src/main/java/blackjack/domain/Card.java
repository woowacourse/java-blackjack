package blackjack.domain;

public class Card {

    private final CardPattern pattern;
    private final CardNumber number;

    public Card(CardPattern pattern, CardNumber number) {
        this.pattern = pattern;
        this.number = number;
    }

    public int getNumber() {
        return number.getValue();
    }

    public String getPattern() {
        return pattern.getName();
    }
}
