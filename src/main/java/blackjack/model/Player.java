package blackjack.model;

import blackjack.model.cards.ChangeableCards;
import java.util.List;

public abstract class Player {

    protected final ChangeableCards cards;
    private final List<Card> openCards;
    private final String name;

    public Player(String name, List<Card> openCards, ChangeableCards cards) {
        this.name = name;
        this.openCards = openCards;
        this.cards = cards;
    }

    public List<Card> openCards() {
        return openCards;
    }

    public void take(Card card) {
        if (!isHittable()) {
            throw new IllegalStateException("카드를 더 이상 발급 받을 수 없습니다.");
        }
        cards.take(card);
    }

    public Score score() {
        return cards.score();
    }

    abstract boolean isHittable();
}
