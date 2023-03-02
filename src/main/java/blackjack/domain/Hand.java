package blackjack.domain;

import java.util.List;

class Hand {
    private final Cards cards;
    private State state;

    public Hand() {
        this.cards = new Cards();
        this.state = State.PLAY;
    }

    public void add(final Card card) {
        cards.add(card);
        if (cards.isBlackjack()) {
            state = State.BLACKJACK;
        }
        if (cards.isBust()) {
            state = State.BUST;
        }
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
            return Result.DRAW;
        }
        return Result.WIN;
    }

    private Result playWithBust(final Hand other) {
        if (other.state.isBust()) {
            return Result.DRAW;
        }
        return Result.LOSE;
    }

    private Result playWithScore(final Hand other) {
        if (cards.calculateTotalScore() > other.cards.calculateTotalScore() || other.state.isBust()) {
            return Result.WIN;
        }
        if (cards.calculateTotalScore() < other.cards.calculateTotalScore() && other.state.isNotBust()) {
            return Result.LOSE;
        }
        return Result.DRAW;
    }

    public List<String> getCardLetters() {
        return cards.getCardLetters();
    }
}
