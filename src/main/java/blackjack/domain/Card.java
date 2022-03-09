package blackjack.domain;

public class Card {

    private final CardNumber number;
    private final CardType type;

    public Card(int number, CardType type) {
        this.type = type;
        this.number = CardNumber.of(number);
    }
}
