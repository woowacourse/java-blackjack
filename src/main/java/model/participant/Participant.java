package model.participant;

import dto.result.ParticipantCurrentHand;
import model.card.Card;

public class Participant {
    private final PlayerName name;
    private final ParticipantHand participantHand = new ParticipantHand();

    public Participant(String playerName) {
        this.name = new PlayerName(playerName);
    }

    public String getName() {
        return this.name.name();
    }

    public void addCard(Card card) {
        participantHand.addDeck(card);
    }

    public Integer getScore() {
        return participantHand.getScore();
    }

    public ParticipantCurrentHand getCurrentHand() {
        return new ParticipantCurrentHand(name.name(), participantHand.getDeck(), participantHand.getScore());
    }

    public boolean isBust() {
        return participantHand.isBust();
    }

    public boolean isBlackJack() {
        return participantHand.isBlackJack();
    }

    protected String getFirstCard() {
        return participantHand.getFirstCard();
    }
}
