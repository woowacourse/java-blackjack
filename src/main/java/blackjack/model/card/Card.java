package blackjack.model.card;

import java.util.Objects;

public class Card {

    private final CardProperties cardProperties;
    private int score;

    private Card(CardProperties cardProperties) {
        this.cardProperties = cardProperties;
        this.score = cardProperties.getCardNumber().getNumber();
    }

    public static Card of(CardPattern cardPattern, CardNumber cardNumber) {
        CardProperties cardProperties = new CardProperties(cardPattern, cardNumber);
        return new Card(cardProperties);
    }

    public CardPattern pattern() {
        return cardProperties.getCardPattern();
    }

    public CardNumber number() {
        return cardProperties.getCardNumber();
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

    public int getScore() {
        return score;
    }
}
