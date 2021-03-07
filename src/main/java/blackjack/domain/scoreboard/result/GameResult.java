package blackjack.domain.scoreboard.result;

import blackjack.domain.card.Cards;
import blackjack.domain.user.ParticipantName;

import java.util.Objects;

public class GameResult implements Resultable {
    private final Cards cards;
    private final ParticipantName participantName;

    public GameResult(Cards cards, String name) {
        this.cards = cards;
        this.participantName = new ParticipantName(name);
    }

    private int calculateScore() {
        return cards.calculateScore();
    }

    @Override
    public Cards getCards() {
        return cards;
    }

    @Override
    public ParticipantName getName() {
        return participantName;
    }

    @Override
    public int getScore() {
        return calculateScore();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameResult that = (GameResult) o;
        return Objects.equals(cards, that.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cards);
    }
}
