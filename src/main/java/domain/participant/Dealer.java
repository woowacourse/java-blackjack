package domain.participant;

import static domain.BlackjackGame.BLACKJACK_SCORE;
import static domain.BlackjackGame.DEALER_MIN_SCORE;
import static domain.BlackjackGame.INITIAL_CARDS;

import domain.BlackjackGame;
import domain.card.Card;
import domain.card.Hand;
import java.util.List;

public class Dealer implements CardOwner {
    private final Hand ownedHand;

    private Dealer() {
        this.ownedHand = Hand.of();
    }

    public static Dealer of() {
        return new Dealer();
    }

    public boolean isBust() {
        return calculateScore() > BlackjackGame.BLACKJACK_SCORE;
    }

    public boolean isBlackjack() {
        return countCard() == INITIAL_CARDS && calculateScore() == BLACKJACK_SCORE;
    }

    @Override
    public void receive(Card card) {
        ownedHand.add(card);
    }

    @Override
    public boolean canReceive() {
        return (calculateScore() <= DEALER_MIN_SCORE);
    }

    @Override
    public int calculateScore() {
        return ownedHand.calculateScore();
    }

    @Override
    public int countCard() {
        return ownedHand.getSize();
    }

    public List<Card> getOwnedCards() {
        return ownedHand.getCards();
    }
}
