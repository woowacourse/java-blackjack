package model;

import dto.Card;
import dto.PlayerName;
import dto.PlayerResult;

public class Participant {
    private final PlayerName name;
    private final ParticipantHand participantHand = new ParticipantHand();

    public Participant(PlayerName name) {
        this.name = name;
    }

    public PlayerResult getResult() {
        return new PlayerResult(name, participantHand.getHand().get(), participantHand.getScore());
    }

    public void draw(Card card) {
        participantHand.addCard(card);
        Integer score = card.cardNumber().getScore();

        participantHand.addScore(score);
    }

    public void addCard(Card card) {
        participantHand.addCard(card);
    }

    public void addScore(Integer score) {
        participantHand.addScore(score);
    }

}
