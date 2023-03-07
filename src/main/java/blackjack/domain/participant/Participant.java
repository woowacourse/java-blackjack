package blackjack.domain.participant;

import static blackjack.domain.game.WinningResult.LOSE;
import static blackjack.domain.game.WinningResult.TIE;
import static blackjack.domain.game.WinningResult.WIN;

import blackjack.domain.card.Card;
import blackjack.domain.game.WinningResult;
import java.util.List;

public abstract class Participant {
    public static final int BLACK_JACK_NUMBER = 21;
    protected final Name name;
    protected Hand hand;

    public Participant(Name name, Hand hand) {
        this.name = name;
        this.hand = hand;
    }

    public void hit(Card card) {
        hand = hand.add(card);
    }

    public void hit(List<Card> cards) {
        hand = hand.add(cards.toArray(Card[]::new));
    }

    public int calculateScore() {
        return hand.calculateScore();
    }

    public List<Card> showCards() {
        return hand.getCards();
    }

    public boolean isBlackJack() {
        return hand.calculateScore() == BLACK_JACK_NUMBER;
    }

    public boolean isBust() {
        return hand.calculateScore() > BLACK_JACK_NUMBER;
    }

    public Name getName() {
        return name;
    }

    public void combat(Participant other) {
        WinningResult winningResult = decideResultByComparingWith(other);
        winningResult.applyTo(this, other);
    }

    private WinningResult decideResultByComparingWith(Participant other) {
        int score = calculateScore();
        int otherScore = other.calculateScore();
        if (isBust()) {
            return LOSE;
        }
        if (score > otherScore || other.isBust()) {
            return WIN;
        }
        if (score < otherScore) {
            return LOSE;
        }
        return TIE;
    }

    public abstract void win();

    public abstract void lose();

    public abstract void tie();
}
