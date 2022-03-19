package blackjack.domain.player;

import java.util.List;
import java.util.Objects;

import blackjack.domain.card.Card;
import blackjack.domain.card.Drawable;
import blackjack.domain.card.HoldCards;

public abstract class Participant {

    protected static final int BLACKJACK_NUMBER = 21;

    protected final HoldCards holdCards;
    protected final Name name;

    public Participant(Name name, HoldCards holdCards) {
        this.name = name;
        this.holdCards = holdCards;
    }

    public abstract boolean canHit();

    public abstract List<Card> openCards();

    public abstract Participant copy();

    public void drawCard(Drawable drawable) {
        holdCards.add(drawable.draw());
    }

    public boolean isBust() {
        return holdCards.getOptimizeTotalNumber() > BLACKJACK_NUMBER;
    }

    public boolean isBlackjack() {
        return holdCards.isInitSize() && holdCards.getOptimizeTotalNumber() == BLACKJACK_NUMBER;
    }

    public int getTotalNumber() {
        return holdCards.getOptimizeTotalNumber();
    }

    public List<Card> getHoldCards() {
        return holdCards.getCards();
    }

    public String getName() {
        return name.getValue();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (!(object instanceof Name))
            return false;
        Name participantName = (Name)object;
        return name.equals(participantName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
