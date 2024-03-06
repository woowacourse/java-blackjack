package blackjack.model.card;

import java.util.Objects;

public class Card {

    private final CardProperties cardPattern;
    private int score;

    public Card(CardProperties cardPattern) {
        this.cardPattern = cardPattern;
        this.score = cardPattern.getCardNumber().getNumber();
    }

    public boolean isElevenAce() {
        return score == 11 && cardPattern.getCardNumber() == CardNumber.ACE;
    }

    public void switchAceValue() {
        if (score == CardNumber.ACE.getNumber()) {
            score = 1;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return cardPattern == card.cardPattern && score == card.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardPattern, score);
    }

    public int getScore() {
        return score;
    }
}
