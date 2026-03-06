package model;

import constant.ErrorMessage;
import model.dto.Card;

public class ParticipantHand {

    private Integer score = 0;
    private final Cards deck = new Cards();

    public void addDeck(Card card) {
        validateCardDuplicate(card);
        deck.add(card);
    }

    public void addScore(Integer score) {
        this.score += score;
    }

    public Integer getScore() {
        return score;
    }

    public Cards getDeck() {
        return deck;
    }

    private void validateCardDuplicate(Card card) {
        if(deck.get().contains(card)) {
            throw new IllegalArgumentException(ErrorMessage.DUPLICATED_CARD_IN_DECK.getMessage());
        }
    }
}
