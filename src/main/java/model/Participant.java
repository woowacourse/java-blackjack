package model;

import dto.Card;
import dto.PlayerResult;

public class Participant {

    private final PlayerName name;
    private final ParticipantHand participantHand = new ParticipantHand();

    public Participant(PlayerName name) {
        this.name = name;
    }

    public PlayerResult getResult() {
        return new PlayerResult(name.value(), participantHand.getHand(), getScore());
    }

    public PlayerName getName() {
        return name;
    }


    public Integer getScore() {
        return participantHand.getScore();
    }

    public int getHandSize() {
        return participantHand.getHandSize();
    }

    public void draw(Card card) {
        participantHand.addCard(card);
        Integer score = card.cardNumber().getScore();
        addScore(score);
    }

    public void addScore(Integer score) {
        participantHand.addScore(score);
    }
}
