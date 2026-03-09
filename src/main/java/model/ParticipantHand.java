package model;

import constant.ErrorMessage;
import dto.Card;
import java.util.List;

public class ParticipantHand {

    private static final int ADDITIONAL_ACE_SCORE = 10;
    private static final int ACE_THRESHOLD = 11;

    private final Cards hand = new Cards();

    public void addCard(Card card) {
        validateCardDuplicate(card);
        hand.add(card);
    }

    public Integer getScore() {
        int baseScore = hand.get().stream()
                .mapToInt(card -> card.cardNumber().getScore())
                .sum();

        boolean hasAce = hand.get().stream()
                .anyMatch(card -> card.cardNumber() == CardNumber.ACE);

        if (hasAce && baseScore <= ACE_THRESHOLD) {
            return baseScore + ADDITIONAL_ACE_SCORE;
        }
        return baseScore;
    }

    public Integer getHandSize() {
        return hand.get().size();
    }

    public List<Card> getHand() {
        return hand.get();
    }

    private void validateCardDuplicate(Card card) {
        if(hand.get().contains(card)) {
            ErrorMessage.DUPLICATED_CARD_IN_DECK.throwException();
        }
    }
}
