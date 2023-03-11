package blackjack.domain.player;

import blackjack.domain.card.Card;

import java.util.List;

public class Hand {
    private final Cards cards;
    private State state;

    public Hand() {
        this.cards = new Cards();
        this.state = State.HIT;
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
            return playWithBust(other);
        }
        return playWithScore(other);
    }

    private Result playWithBlackjack(final Hand other) {
        if (other.state.isBlackjack()) {
            return Result.PUSH;
        }
        return Result.WIN;
    }

    private Result playWithBust(final Hand other) {
        if (other.state.isBust()) {
            return Result.PUSH;
        }
        return Result.LOSE;
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
        state = State.STAY;
    }

    public boolean isBlackjack() {
        return state.isBlackjack();
    }

    public boolean isBust() {
        return state.isBust();
    }

    public List<String> getCardLetters() {
        return cards.getCardLetters();
    }
}
