package model;

import model.dto.Card;
import model.dto.PlayerName;
import model.dto.PlayerResult;

public class Participant {
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
    }

    /*점수 갱신(int 점수 받기)*/
    public void addScore(Integer score) {
        participantHand.addScore(score);
    }

}
