package domain.participant;

import static constant.GameRule.BLACKJACK_CRITERION;
import static message.ErrorMessage.INITIAL_CARD_SIZE_MISMATCH;
import static message.ErrorMessage.PARTICIPANT_MUST_NOT_HAVE_INITIAL_CARDS;

import domain.card.Card;
import domain.card.Hand;
import java.util.List;

public abstract class Participant {

    public static final int INITIAL_CARD_SIZE = 2;

    private final Hand hand = new Hand();

    public void addCard(Card card) {
        hand.add(card);
    }

    public void receiveInitialCards(List<Card> cards) {
        validateCardSize(cards);
        validateHandSize();

        hand.addAll(cards);
    }

    private void validateCardSize(List<Card> cards) {
        if (cards.size() != INITIAL_CARD_SIZE) {
            throw new IllegalArgumentException(INITIAL_CARD_SIZE_MISMATCH.getMessage());
        }
    }

    private void validateHandSize() {
        if (hand.getSize() != 0) {
            throw new IllegalArgumentException(PARTICIPANT_MUST_NOT_HAVE_INITIAL_CARDS.getMessage());
        }
    }

    public int getScore() {
        return hand.calculateScore();
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public List<Card> getHand() {
        return hand.getCards();
    }

    public boolean isBlackjack() {
        return hand.getSize() == 2 && hand.calculateScore() == BLACKJACK_CRITERION;
    }

    public abstract boolean canDraw();
}
