package model;

import model.dto.Card;
import model.dto.PlayerName;
import model.dto.PlayerResult;

public class Participant {
    private static final Integer BUST_SCORE = 21;

    private final PlayerName name;
    private final ParticipantHand participantHand = new ParticipantHand();

    public Participant(PlayerName name) {
        this.name = name;
    }

    public PlayerResult getResult() {
        return new PlayerResult(name, participantHand.getDeck().get(), participantHand.getScore());
    }

    public void addCard(Card card) {
        participantHand.addDeck(card);
        participantHand.addScore(card.cardNumber().getScore());
    }

    public boolean isMoreThanScore(Integer threshold) {
        return participantHand.getScore() > threshold;
    }

    public boolean isBust() {
        return isMoreThanScore(BUST_SCORE);
    }
}
