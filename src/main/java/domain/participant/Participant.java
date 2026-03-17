package domain.participant;

import domain.card.Card;
import java.util.List;

public abstract class Participant {
    private static final int BLACKJACK_SCORE = 21;
    private static final int ACE_ADDITIONAL_SCORE = 10;

    private final List<Card> hand;

    public Participant(List<Card> hand) {
        this.hand = hand;
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public int calculateScore() {
        int results = 0;
        for (Card holdCard : hand) {
            results += holdCard.getCardValue().getScore();
        }

        return applyAce(results);
    }

    public boolean isBlackjack() {
        return hand.size() == 2 && calculateScore() == BLACKJACK_SCORE;
    }

    public boolean isBust() {
        return calculateScore() > BLACKJACK_SCORE;
    }

    public boolean isSoftHand(boolean isAceExist, int results) {
        return isAceExist && (results + ACE_ADDITIONAL_SCORE) <= BLACKJACK_SCORE;
    }

    private int applyAce(int results) {
        boolean isAceExist = hand.stream().anyMatch(Card::isAce);
        if (isSoftHand(isAceExist, results)) {
            return results + ACE_ADDITIONAL_SCORE;
        }
        return results;
    }

    public List<Card> getHand() {
        return List.copyOf(hand);
    }
}
