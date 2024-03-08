package blackjack.model.card;

import java.util.Objects;

public class Card {

    private final CardProperties cardProperties;
    private int score;

    public Card(CardProperties cardProperties) {
        this.cardProperties = cardProperties;
        this.score = cardProperties.getCardNumber().getNumber();
    }

    public boolean isElevenAce() {
        return score == 11 && cardProperties.getCardNumber() == CardNumber.ACE;
    }

    public void switchAceValue() {
        if (score == CardNumber.ACE.getNumber()) {
            score = 1;
        }
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Card card = (Card) object;
        return score == card.score && Objects.equals(cardProperties, card.cardProperties);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardProperties, score);
    }

    public CardProperties getCardProperties() {
        return cardProperties;
    }

    public int getScore() {
        return score;
    }
}
