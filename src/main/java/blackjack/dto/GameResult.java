package blackjack.dto;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Participant;
import java.util.List;

public class GameResult {

    private final String name;
    private final List<Card> cards;
    private final int score;

    public GameResult(final Participant participant) {
        this.name = participant.getName();
        this.cards = participant.getCards();
        this.score = participant.calculateScore();
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
