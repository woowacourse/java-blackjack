package blackjack.domain.card;

public class Card {

    private final CardPattern pattern;
    private final CardNumber number;

    public Card(CardPattern pattern, CardNumber number) {
        this.pattern = pattern;
        this.number = number;
    }

    public boolean isContain(CardPattern pattern, CardNumber number) {
        return this.pattern.equals(pattern) && this.number.equals(number);
    }

    public boolean isAce() {
        return this.number == CardNumber.ACE;
    }

    public int getNumberValue() {
        return number.getValue();
    }

    public String getNumberName() {
        return number.getName();
    }

    public String getPatternName() {
        return pattern.getName();
    }
}
