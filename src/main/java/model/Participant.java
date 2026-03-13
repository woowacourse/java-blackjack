package model;

import dto.PlayerResult;

public class Participant {

    private final PlayerName name;
    private final ParticipantHand participantHand;

    public Participant(PlayerName name) {
        this.name = name;
        this.participantHand = new ParticipantHand();
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
    }
}
