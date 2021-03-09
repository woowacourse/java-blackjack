package blackjack.domain.participant;

import blackjack.domain.Hand;
import blackjack.domain.Money;
import blackjack.domain.card.Card;
import blackjack.exception.InvalidNameInputException;

import java.util.ArrayList;
import java.util.Objects;

public abstract class BlackJackParticipant {

    private final Hand hand;
    private final Name name;
    private boolean hit;
    private Money money;

    public BlackJackParticipant(String name) {
        this.hand = new Hand(new ArrayList<>());
        this.hit = true;
        this.name = new Name(name);
        this.money = new Money();
    }

    abstract public void draw(Card card);

    public int getScore() {
        return hand.getScore();
    }

    public Hand getHand() {
        return hand;
    }

    public String getName() {
        return name.value();
    }

    public double getMoney() {
        return money.getValue();
    }

    protected void cannotDraw() {
        this.hit = false;
    }

    public boolean isContinue() {
        return hit;
    }

    public boolean isBust() {
        return hand.isBust();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BlackJackParticipant that = (BlackJackParticipant) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
