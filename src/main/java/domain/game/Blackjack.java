package domain.game;

import domain.card.Card;
import domain.card.CardBundle;
import java.util.List;

public class Blackjack implements HandState {

    private final CardBundle cardBundle;

    public Blackjack(CardBundle cardBundle) {
        this.cardBundle = cardBundle;
    }

    @Override
    public Outcome versus(HandState other) {
        if (other instanceof Blackjack) {
            return Outcome.TIE;
        }
        return Outcome.BLACKJACK_WIN;
    }

    @Override
    public boolean canHit() {
        return false;
    }

    @Override
    public HandState draw(Card card) {
        return this;
    }

    @Override
    public HandState stay() {
        return this;
    }

    @Override
    public List<Card> cards() {
        return cardBundle.getCards();
    }

    @Override
    public int score() {
        return cardBundle.calculateScore();
    }
}
