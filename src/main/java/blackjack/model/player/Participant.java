package blackjack.model.player;

import blackjack.model.card.Card;
import blackjack.model.cards.BestScoreCalculator;
import blackjack.model.cards.Cards;
import blackjack.model.cards.Score;

public abstract class Participant {

    private static final BestScoreCalculator BEST_SCORE_CALCULATOR = new BestScoreCalculator();
    private static final int BLACKJACK_CARD_SIZE = 2;
    private static final Score BLACKJACK_SCORE = new Score(21);

    private final Cards cards;
    private final String name;

    public Participant(String name, Card card1, Card card2, Card... cards) {
        this(name, new Cards(card1, card2, cards));
    }

    protected Participant(String name, Cards cards) {
        this.name = name;
        this.cards = cards;
    }

    public final String name() {
        return name;
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
        return BEST_SCORE_CALCULATOR.calculate(cards);
    }

    public final boolean isBust() {
        return score().isBust();
    }

    public final boolean isBlackjack() {
        return isBlackjackCardSize() && isBlackjackScore();
    }

    private boolean isBlackjackCardSize() {
        return cards().size() == BLACKJACK_CARD_SIZE;
    }

    private boolean isBlackjackScore() {
        return score().equals(BLACKJACK_SCORE);
    }

    public final boolean lessScoreThan(Participant other) {
        return score().lessThan(other.score());
    }

    public final boolean moreScoreThan(Participant other) {
        return score().moreThan(other.score());
    }

    public abstract Cards openCards();

    public abstract boolean isHittable();
}
