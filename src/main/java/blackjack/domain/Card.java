package blackjack.domain;

public class Card {

    private final Number number;
    private final Pattern pattern;

    public Card(final Number number, final Pattern pattern) {
        this.number = number;
        this.pattern = pattern;
    }

    public int convertToScore() {
        return Number.convertNumberToScore(number);
    }

    public boolean isAce() {
        return this.number == Number.ACE;
    }
}
