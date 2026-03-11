package model;

import constant.ErrorMessage;
import java.util.List;

public class ParticipantHand {
    private static final Integer MAX_ACE_SCORE = 11;
    private static final Integer ADDITIONAL_ACE_SCORE = 10;

    private final Score score = new Score();
    private final Cards deck = new Cards();

    public void addDeck(Card card) {
        validateCardDuplicate(card);
        deck.add(card);
        score.add(card.cardNumber().getScore());
    }

    public Integer getScore() {
        if(deck.hasAce() && score.get() <= MAX_ACE_SCORE) {
            return score.get() + ADDITIONAL_ACE_SCORE;
        }
        return score.get();
    }

    public List<Card> getDeck() {
        return deck.get();
    }

    public String getFirstCard() {
        return deck.getFirstCard().getString();
    }

    private void validateCardDuplicate(Card card) {
        if(deck.get().contains(card)) {
            throw new IllegalArgumentException(ErrorMessage.DUPLICATED_CARD_IN_DECK.getMessage());
        }
    }
}
