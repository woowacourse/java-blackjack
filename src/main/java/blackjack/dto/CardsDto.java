package blackjack.dto;

import blackjack.domain.card.Card;
import blackjack.domain.game.Score;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class CardsDto {

    private final Set<Card> cards;
    private final Score score;

    public CardsDto(Set<Card> cards, Score score) {
        this.cards = new HashSet<>(cards);
        this.score = score;
    }

    public Set<Card> getCards() {
        return Collections.unmodifiableSet(cards);
    }

    public Score getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "CardBundleDto{" +
                "cards=" + cards +
                ", score=" + score +
                '}';
    }
}
