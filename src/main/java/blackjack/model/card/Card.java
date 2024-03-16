package blackjack.model.card;

import java.util.Objects;

public class Card {

    private final CardProperties cardProperties;
    private Score score;

    private Card(CardProperties cardProperties) {
        this.cardProperties = cardProperties;
        this.score = new Score(cardProperties.getCardNumber().getNumber());
    }

    public Card(CardPattern cardPattern, CardNumber cardNumber) {
        this(new CardProperties(cardPattern, cardNumber));
    }

    public CardPattern pattern() {
        return cardProperties.getCardPattern();
    }

    public CardNumber number() {
        return cardProperties.getCardNumber();
    }

    public boolean isElevenAce() {
        return score.getScore() == 11 && cardProperties.getCardNumber() == CardNumber.ACE;
    }

    public void switchAceValue() {
        if (score.getScore() == CardNumber.ACE.getNumber()) {
            score = new Score(1);
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
        return score.getScore() == card.score.getScore() && Objects.equals(cardProperties, card.cardProperties);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardProperties, score.getScore());
    }

    public int getScore() {
        return score.getScore();
    }
}
