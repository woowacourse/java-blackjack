package blackjack.dto.score;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Participant;

import java.util.List;

public class ScoreDto {

    private final String name;
    private final List<Card> cards;
    private final int score;

    public ScoreDto(Participant participant) {
        this.name = participant.getName();
        this.cards = participant.getCards();
        this.score = participant.getScore();
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
