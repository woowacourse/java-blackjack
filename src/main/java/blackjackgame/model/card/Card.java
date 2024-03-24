package blackjackgame.model.card;

import java.util.Objects;

public class Card {

    private final CardNumber number;
    private final CardShape shape;

    public Card(CardDispenser cardDispenser) {
        this.number = cardDispenser.generateCardNumber();
        this.shape = cardDispenser.generateCardShape();
    }

    public boolean isAce() {
        return number.isAce(number);
    }

    public String cardTypeLettering() {
        return number.getNumber() + shape.getShape();
    }

    public int score() {
        return number.getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Card card = (Card) o;
        return number == card.number && shape == card.shape;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, shape);
    }
}
