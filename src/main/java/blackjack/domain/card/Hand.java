package blackjack.domain.card;

import blackjack.domain.player.Result;
import java.util.ArrayList;
import java.util.List;

public class Hand {
    private final Cards cards;
    private State state;

    public Hand() {
        this(new ArrayList<>());
    }

    public Hand(final List<Card> cards) {
        this.cards = new Cards(cards);
        this.state = State.calculateState(this.cards);
    }

    public void add(final Card card) {
        cards.add(card);
        state = State.calculateState(cards);
    }

    public Result play(final Hand other) {
        if (state.isBlackjack()) {
            return playWithBlackjack(other);
        }
        if (state.isBust()) {
            return Result.LOSE;
        }
        return playWithScore(other);
    }

    private Result playWithBlackjack(final Hand other) {
        if (other.state.isBlackjack()) {
            return Result.PUSH;
        }
        return Result.BLACKJACK_WIN;
    }

    private Result playWithScore(final Hand other) {
        if (other.state.isBust() || cards.calculateTotalScore() > other.cards.calculateTotalScore()) {
            return Result.WIN;
        }
        if (other.state.isNotBust() && cards.calculateTotalScore() < other.cards.calculateTotalScore()) {
            return Result.LOSE;
        }
        return Result.PUSH;
    }

    public boolean isPlayable() {
        return state.isPlayable();
    }

    public int calculateScore() {
        return cards.calculateTotalScore();
    }

    public void stay() {
        state = State.STOP;
    }

    public List<String> getCardLetters() {
        return cards.getCardLetters();
    }
}
