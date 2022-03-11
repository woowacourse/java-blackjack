package blackjack.model.player;

import blackjack.model.card.Card;
import blackjack.model.blackjack.Score;
import blackjack.model.cards.Cards;
import blackjack.model.cards.ScoreCards;

public class Player {

    public static final Score HIT_BOUNDARY = new Score(21);
    public static final int OPEN_CARD_COUNT = 2;

    protected final ScoreCards cards;
    private final Name name;

    public Player(Name name, Card card1, Card card2, Card... cards) {
        this(name, Cards.of(card1, card2, cards));
    }

    protected Player(Name name, Cards cards) {
        this.name = name;
        this.cards = Cards.bestScoreCards(cards);
    }

    public final Name name() {
        return name;
    }

    public Cards openCards() {
        return Cards.toUnmodifiable(cards.openCard(OPEN_CARD_COUNT));
    }

    public final Cards cards() {
        return Cards.toUnmodifiable(cards);
    }

    public final void take(Card card) {
        if (!isHittable()) {
            throw new IllegalStateException("카드를 더 이상 발급 받을 수 없습니다.");
        }
        cards.take(card);
    }

    public final Score score() {
        return cards.score();
    }

    public final boolean isBust() {
        return score().isBust();
    }

    public final boolean isSameName(Name name) {
        return name.equals(name);
    }

    public boolean isHittable() {
        return cards.lessThan(HIT_BOUNDARY);
    }
}
