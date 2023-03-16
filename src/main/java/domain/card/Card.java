package domain.card;

import java.util.Objects;

public class Card {
    
    private final CardNumber number;
    
    private final CardShape shape;
    
    public Card(CardNumber number, CardShape shape) {
        this.number = number;
        this.shape = shape;
    }
    
    public CardNumber getCardNumber() {
        return this.number;
    }
    
    public CardShape getCardShape() {
        return this.shape;
    }
    
    public Score getScore() {
        return new Score(this.number.getNumber());
    }
    
    public boolean isAce() {
        return this.number == CardNumber.ACE;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(this.number, this.shape);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Card card = (Card) o;
        return this.number == card.number && this.shape == card.shape;
    }
}
