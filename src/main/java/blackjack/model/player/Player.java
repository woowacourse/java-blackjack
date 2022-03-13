package blackjack.model.player;

import blackjack.model.card.Card;
import blackjack.model.score.BestScoreCalculator;
import blackjack.model.score.Score;

public class Player {

    private static final BestScoreCalculator CALCULATOR = new BestScoreCalculator();
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
        return new Cards(cards);
    }

    public final void take(Card card) {
        if (!isHittable()) {
            throw new IllegalStateException("카드를 더 이상 발급 받을 수 없습니다.");
        }
        cards.take(card);
    }

    public final Score score() {
        return CALCULATOR.calculate(cards);
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
        return CALCULATOR.calculate(cards).lessThan(HIT_BOUNDARY);
    }
}
