package model;

import java.util.List;

public class Participant {

    private final PlayerName name;
    private final ParticipantHand participantHand;

    public Participant(PlayerName name) {
        this.name = name;
        this.participantHand = new ParticipantHand();
    }

    public PlayerName getName() {
        return name;
    }

    public String getNameValue() {
        return name.value();
    }

    public List<Card> getHand() {
        return participantHand.getHand();
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
