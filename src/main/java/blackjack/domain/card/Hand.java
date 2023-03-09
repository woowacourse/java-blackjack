package blackjack.domain.card;

import static blackjack.domain.card.State.BLACKJACK;
import static blackjack.domain.card.State.BUST;
import static blackjack.domain.card.State.PLAY;
import static blackjack.domain.card.State.STOP;
import static blackjack.domain.card.State.calculateState;

import blackjack.domain.player.Result;
import java.util.ArrayList;
import java.util.List;

public final class Hand {
    private final Cards cards;
    private State state;

    public Hand() {
        this(new ArrayList<>());
    }

    public Hand(final List<Card> cards) {
        this.cards = new Cards(cards);
        this.state = calculateState(this.cards);
    }

    public void add(final Card card) {
        cards.add(card);
        state = calculateState(cards);
    }

    public Result play(final Hand other) {
        if (state == BLACKJACK) {
            return playWithBlackjack(other);
        }
        if (state == BUST) {
            return Result.LOSE;
        }
        return playWithScore(other);
    }

    private Result playWithBlackjack(final Hand other) {
        if (other.state == BLACKJACK) {
            return Result.PUSH;
        }
        return Result.BLACKJACK_WIN;
    }

    private Result playWithScore(final Hand other) {
        if (other.state == BUST || cards.totalScore() > other.cards.totalScore()) {
            return Result.WIN;
        }
        if (other.state != BUST && cards.totalScore() < other.cards.totalScore()) {
            return Result.LOSE;
        }
        return Result.PUSH;
    }

    public boolean isPlayable() {
        return state == PLAY;
    }

    public int score() {
        return cards.totalScore();
    }

    public void stay() {
        state = STOP;
    }

    public List<String> getCardLetters() {
        return cards.getCardLetters();
    }
}
