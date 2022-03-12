package blackjack.dto;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Participant;
import java.util.List;

public class ParticipantScoreResult {

    private final String name;
    private final List<Card> cards;
    private final int score;

    private ParticipantScoreResult(final String name, final List<Card> cards, final int score) {
        this.name = name;
        this.cards = cards;
        this.score = score;
    }

    public static ParticipantScoreResult from(final Participant participant) {
        return new ParticipantScoreResult(
                participant.getName(), participant.cards(), participant.calculateResultScore()
        );
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards;
    }

    public int getScore() {
        return score;
    }
}
