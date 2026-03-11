package model;

import java.util.List;
import java.util.Objects;
import model.dto.PlayerResult;

public class Participant {
    private static final Integer BUST_SCORE = 21;
    private static final Integer BLACK_JACK_CARD_SIZE = 2;

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
        return new PlayerResult(getName(), cards, participantHand.getScore());
    }

    public void addCard(Card card) {
        participantHand.addDeck(card);
    }

    public boolean isMoreThanScore(Integer threshold) {
        return participantHand.getScore() > threshold;
    }

    public boolean isBust() {
        return isMoreThanScore(BUST_SCORE);
    }

    public boolean isBlackJack() {
        return participantHand.getDeck().size() == BLACK_JACK_CARD_SIZE && Objects.equals(participantHand.getScore(), BUST_SCORE);
    }

    public List<Card> getDeck() {
        return List.copyOf(participantHand.getDeck());
    }

    public boolean isMoreScore(Participant other) {
        return this.participantHand.getScore() > other.participantHand.getScore();
    }
}
