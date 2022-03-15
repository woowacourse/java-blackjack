package blackjack.domain.card;

public class Card {

    private final CardNumber number;
    private final CardType type;

    public Card(CardNumber number, CardType type) {
        this.type = type;
        this.number = number;
    }

    public int getNumber() {
        return number.getNumber();
    }

    public CardNumber getCardNumber() {
        return number;
    }

    public CardType getType() {
        return type;
    }
}
