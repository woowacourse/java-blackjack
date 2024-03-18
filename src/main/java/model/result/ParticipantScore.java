package model.result;

import java.util.List;
import model.participant.Participant;

public class ParticipantScore {

    private final CardDto card;
    private final int score;

    private ParticipantScore(CardDto card, int score) {
        this.card = card;
        this.score = score;
    }

    public static ParticipantScore from(Participant participant) {
        CardDto card = new CardDto(participant.getName(), participant.getCardsInfo());
        int score = participant.score();
        return new ParticipantScore(card, score);
    }

    public List<String> cards() {
        return card.cards();
    }

    public CardDto getCard() {
        return card;
    }

    public int getScore() {
        return score;
    }
}
