package blackjack.domain;

public class Card {

    private final CardPattern pattern;
    private final int number;

    public Card(final CardPattern pattern, final int number) {
        this.pattern = pattern;
        this.number = number;
    }

}
