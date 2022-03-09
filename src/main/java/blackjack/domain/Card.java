package blackjack.domain;

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

    public String getOriginalNumber() {
        return number.getOriginalName();
    }

    public String getCardType() {
        return type.getName();
    }
}
