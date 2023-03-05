package blackjack.domain.participant;

import static blackjack.domain.game.Result.LOSE;
import static blackjack.domain.game.Result.TIE;
import static blackjack.domain.game.Result.WIN;

import blackjack.domain.game.Result;
import java.util.List;

import blackjack.domain.card.Card;

public abstract class Participant {
    protected final Name name;
    protected final Hand hand;

    public Participant(Name name, Hand hand) {
        this.name = name;
        this.hand = hand;
    }

    public void hit(Card card) {
        hand.add(card);
    }

    public void hit(List<Card> cards) {
        cards.forEach(hand::add);
    }

    public int calculateScore() {
        return hand.calculateScore();
    }

    public List<Card> showCards() {
        return hand.getCards();
    }

    public boolean isBust() {
        return hand.calculateScore() >= 22;
    }

    public Name getName() {
        return name;
    }

    public void combat(Participant other) {
        Result result = decideResultByComparingWith(other);
        result.accept(this, other);
    }

    private Result decideResultByComparingWith(Participant other) {
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
