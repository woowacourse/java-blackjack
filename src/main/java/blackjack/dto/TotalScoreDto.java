package blackjack.dto;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Participant;

import java.util.List;

public class TotalScoreDto {

    private final String name;
    private final List<Card> cards;
    private final int score;

    public TotalScoreDto(Participant participant) {
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
