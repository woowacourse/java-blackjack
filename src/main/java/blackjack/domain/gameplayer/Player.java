package blackjack.domain.gameplayer;

import blackjack.domain.Score;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Player implements Person {

    private final Name name;
    private final List<Card> cards;

    public Player(Name name) {
        this.name = name;
        this.cards = new ArrayList<>();
    }

    @Override
    public void addCard(Card card) {
        cards.add(card);
    }

    @Override
    public List<Card> showCards() {
        return Collections.unmodifiableList(cards);
    }

    public String showName() {
        return name.getName();
    }

    @Override
    public boolean isHit() {
        Score totalScore = calculateScore();
        return totalScore.isLessThan(Score.PLAYER_HIT_UPPER_BOUND);
    }

    @Override
    public Score calculateScore() {
        return Score.from(cards);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name) && Objects.equals(cards, player.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cards);
    }
}
