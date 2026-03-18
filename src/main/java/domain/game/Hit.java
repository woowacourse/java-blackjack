package domain.game;

import domain.card.Card;
import domain.card.CardBundle;
import java.util.List;

public class Hit implements HandState {

    private final CardBundle cardBundle;

    public Hit(CardBundle cardBundle) {
        this.cardBundle = cardBundle;
    }

    @Override
    public Outcome versus(HandState other) {
        return new Stay(cardBundle).versus(other);
    }

    @Override
    public boolean canHit() {
        return true;
    }

    @Override
    public HandState draw(Card card) {
        cardBundle.addCard(card);
        if (cardBundle.isBlackjack()) {
            return new Blackjack(cardBundle);
        }
        if (cardBundle.isBust()) {
            return new Bust(cardBundle);
        }
        return this;
    }

    @Override
    public HandState stay() {
        return new Stay(cardBundle);
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
