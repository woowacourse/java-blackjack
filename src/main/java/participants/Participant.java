package participants;

import java.util.List;
import java.util.Objects;

import card.Card;
import card.Score;

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

    public Score calculateScore() {
        return hand.calculateScore();
    }

    public List<Card> showCards() {
        return hand.getCards();
    }

    public boolean isBust() {
        return hand.calculateScore().isOverMaxScore();
    }

    public Name getName() {
        return name;
    }

    public boolean isBlackjack() {
        return hand.isBlackjack();
    }

    public void compareScoreWith(Participant other) {
        if (isBlackjackWinTo(other)) {
            this.winBlackjack();
            other.lose();
            return;
        }
        if (isWinTo(other)) {
            this.win();
            other.lose();
            return;
        }
        if (isLoseTo(other)) {
            this.lose();
            other.win();
            return;
        }
        if (isDrawTo(other)) {
            this.tie();
            other.tie();
        }
    }

    private boolean isBlackjackWinTo(Participant other) {
        return this.hand.isBlackjack() && !other.hand.isBlackjack();
    }

    private boolean isWinTo(Participant other) {
        if (this.isBust()) {
            return false;
        }
        if (other.isBust()) {
            return true;
        }
        return this.calculateScore().isBiggerThan(other.calculateScore());
    }

    private boolean isLoseTo(Participant other) {
        if (this.isBust()) {
            return true;
        }
        if (!this.isBlackjack() && other.isBlackjack()) {
            return true;
        }
        if (other.isBust()) {
            return false;
        }
        return other.calculateScore().isBiggerThan(this.calculateScore());
    }

    private boolean isDrawTo(Participant other) {
        return other.calculateScore().equals(this.calculateScore()) && !this.isBust();
    }

    protected abstract void winBlackjack();

    protected abstract void win();

    protected abstract void lose();

    protected abstract void tie();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Participant that = (Participant) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
