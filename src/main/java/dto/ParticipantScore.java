package dto;

import java.util.List;
import model.game.Score;
import model.participant.Participant;

public class ParticipantScore {

    private static final int BURST_CONDITION = 22;
    private final ParticipantCard card;
    private final int score;

    private ParticipantScore(ParticipantCard card, int score) {
        this.card = card;
        this.score = score;
    }

    public static ParticipantScore from(Participant participant) {
        ParticipantCard card = ParticipantCard.createWithAllCard(participant);
        int score = Score.from(participant.getCards()).getValue();
        return new ParticipantScore(card, score);
    }


    public boolean isNotBurst() {
        return !isBurst();
    }

    public boolean isBurst() {
        return score >= BURST_CONDITION;
    }

    public List<String> cards() {
        return card.getCards();
    }

    public ParticipantCard getCard() {
        return card;
    }

    public int getScore() {
        return score;
    }
}
