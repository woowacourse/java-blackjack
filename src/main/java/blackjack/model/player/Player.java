package blackjack.model.player;

import blackjack.model.card.Card;
import blackjack.model.cards.BestScoreCalculator;
import blackjack.model.cards.Cards;
import blackjack.model.cards.Score;

public class Player {

    private static final BestScoreCalculator BEST_SCORE_CALCULATOR = new BestScoreCalculator();
    private static final Score HIT_BOUNDARY = new Score(21);
    private static final int OPEN_CARD_COUNT = 2;

    private final Cards cards;
    private final Name name;

    public Player(Name name, Card card1, Card card2, Card... cards) {
        this(name, new Cards(card1, card2, cards));
    }

    protected Player(Name name, Cards cards) {
        this.name = name;
        this.cards = cards;
    }

    public final Name name() {
        return name;
    }

    public Cards openCards() {
        return cards.openedCards(OPEN_CARD_COUNT);
    }

    public final Cards cards() {
        return cards;
    }

    public final void take(Card card) {
        if (!isHittable()) {
            throw new IllegalStateException("카드를 더 이상 발급 받을 수 없습니다.");
        }
        cards.take(card);
    }

    public final Score score() {
        return BEST_SCORE_CALCULATOR.score(cards);
    }

    public final boolean isBust() {
        return score().isBust();
    }

    protected final boolean lessScoreThan(Player other) {
        return score().lessThan(other.score());
    }

    protected final boolean moreScoreThan(Player other) {
        return score().moreThan(other.score());
    }

    public boolean isHittable() {
        return score().lessThan(HIT_BOUNDARY);
    }
}
