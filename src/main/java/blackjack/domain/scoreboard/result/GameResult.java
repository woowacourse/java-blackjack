package blackjack.domain.scoreboard.result;

import blackjack.domain.card.Card;
import blackjack.domain.user.Name;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class GameResult implements Resultable {
    private final List<Card> cards;
    private final Name name;

    public GameResult(List<Card> cards, String name) {
        this.cards = cards;
        this.name = new Name(name);
    }

    private int calculateScore() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    @Override
    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    @Override
    public Name getName() {
        return name;
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
