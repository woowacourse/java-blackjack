package domain;

import java.util.List;

public abstract class Participant {
    private final List<Card> hand;

    public Participant(List<Card> hand) {
        this.hand = hand;
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public int calculateTotalScore() {
        int results = 0;
        for (Card holdCard : hand) {
            results += holdCard.getScore();
        }

        boolean isAceExist = hand.stream()
                .anyMatch(holdCard -> holdCard.getCardNumber() == CardNumber.ACE);
        if (isAceExist && (results + 10) <= 21) {
            return results + 10;
        }
        return results;
    }

    public List<Card> getHand(){
        return List.copyOf(hand);
    }

    public boolean isBust() {
        return calculateTotalScore() > 21;
    }
}
