package blackjack.model;

import blackjack.model.cards.Cards;
import blackjack.model.cards.ImmutableCards;
import blackjack.model.cards.OwnCards;
import java.util.List;

public abstract class Player {

    protected final OwnCards cards;
    private final List<Card> openCards;
    private final String name;

    public Player(String name, List<Card> openCards, OwnCards cards) {
        this.name = name;
        this.openCards = openCards;
        this.cards = cards;
    }

    public String name() {
        return name;
    }

    public List<Card> openCards() {
        return openCards;
    }

    public Cards cards() {
        return new ImmutableCards(cards);
    }

    public void take(Card card) {
        if (!isHittable()) {
            throw new IllegalStateException("카드를 더 이상 발급 받을 수 없습니다.");
        }
        cards.take(card);
    }

    public Score score() {
        return cards.bestScore();
    }

    public boolean isBust() {
        return score().isBust();
    }

    public abstract boolean isHittable();
}
