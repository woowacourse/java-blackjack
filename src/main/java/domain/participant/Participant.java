package domain.participant;

import domain.card.Card;
import domain.card.CardValue;
import java.util.List;

public abstract class Participant {
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
            results += holdCard.getScore();
        }

        boolean isAceExist = hand.stream()
                .anyMatch(holdCard -> holdCard.getCardNumber() == CardValue.ACE);
        if (isSoftHand(isAceExist, results)) {
            return results + 10;
        }
        return results;
    }

    public boolean isBlackjack() {
        return hand.size() == 2 && calculateScore() == 21;
    }

    public boolean isBust() {
        return calculateScore() > 21;
    }

    public boolean isSoftHand(boolean isAceExist, int results) {
        return isAceExist && (results + 10) <= 21;
    }

    public List<Card> getHand() {
        return List.copyOf(hand);
    }
}
