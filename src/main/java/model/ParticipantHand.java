package model;

import exception.GameException;
import dto.Card;
import java.util.ArrayList;
import java.util.List;

public class ParticipantHand {

    private static final int ADDITIONAL_ACE_SCORE = 10;
    private static final int BLACKJACK_SCORE = 21;

    private final List<Card> hand;

    public ParticipantHand() {
        this.hand = new ArrayList<>();
    }

    public void addCard(Card card) {
        validateCardDuplicate(card);
        hand.add(card);
    }

    public int getScore() {
        int baseScore = hand.stream().mapToInt(card -> card.cardNumber().getScore()).sum();

        boolean hasAce = hand.stream().anyMatch(card -> card.cardNumber() == CardNumber.ACE);

        if (hasAce && baseScore + ADDITIONAL_ACE_SCORE <= BLACKJACK_SCORE) {
            return baseScore + ADDITIONAL_ACE_SCORE;
        }
        return baseScore;
    }

    public int getHandSize() {
        return hand.size();
    }

    public List<Card> getHand() {
        return List.copyOf(hand);
    }

    private void validateCardDuplicate(Card card) {
        if (hand.contains(card)) {
            throw new GameException("덱에 중복된 카드가 있습니다.");
        }
    }
}
