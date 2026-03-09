package model;

import constant.ErrorMessage;
import dto.Card;

public class ParticipantHand {

    private final Score score = new Score();
    private final Cards hand = new Cards();

    public void addCard(Card card) {
        validateCardDuplicate(card);
        hand.add(card);
    }

    public void addScore(Integer score) {
        this.score.add(score);
    }

    public Integer getScore() {
        return score.get();
    }

    public Cards getHand() {
        return hand;
    }

    private void validateCardDuplicate(Card card) {
        if(hand.get().contains(card)) {
            ErrorMessage.DUPLICATED_CARD_IN_DECK.throwException();
        }
    }
}
