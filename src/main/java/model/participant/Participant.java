package model.participant;

import java.util.List;
import model.card.Card;
import dto.result.PlayerResult;

public class Participant {
    private final PlayerName name;
    private final ParticipantHand participantHand = new ParticipantHand();

    public Participant(PlayerName name) {
        this.name = name;
    }

    public String getName() {
        return this.name.getName();
    }

    public PlayerResult getResult() {
        List<String> cards = participantHand.getDeck().stream().map(Card::getString).toList();
        return new PlayerResult(getName(), cards, participantHand.getScore(), 0);
    }

    public void addCard(Card card) {
        participantHand.addDeck(card);
    }

    public Integer getScore() {
        return participantHand.getScore();
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
