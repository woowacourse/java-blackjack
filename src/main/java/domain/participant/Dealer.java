package domain.participant;

import static domain.BlackjackGame.DEALER_MIN_SCORE;

import domain.BlackjackGame;
import domain.card.Card;
import domain.card.Hand;
import java.util.List;

public class Dealer {
    private final Hand ownedHand;

    private Dealer() {
        this.ownedHand = Hand.of();
    }

    public static Dealer of() {
        return new Dealer();
    }

    public void receive(Card card) {
        ownedHand.add(card);
    }

    public List<Card> getOwnedCards() {
        return ownedHand.getCards();
    }

    public boolean canReceive() {
        return (getScore() <= DEALER_MIN_SCORE);
    }

    public boolean isBust() {
        return getScore() > BlackjackGame.BLACKJACK_SCORE;
    }

    public int getScore() {
        return ownedHand.calculateScore();
    }

    public int getCardCount() {
        return ownedHand.getSize();
    }
}
