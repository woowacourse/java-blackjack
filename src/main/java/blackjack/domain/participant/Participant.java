package blackjack.domain.participant;

import blackjack.domain.Hand;
import blackjack.domain.card.Card;
import java.util.List;
import java.util.Objects;

public class Participant {

    protected Name name;
    protected final Hand cardHand = new Hand();

    public String getName() {
        return name.getValue();
    }

    public Hand getCardHand() {
        return cardHand;
    }

    public int getScore() {
        return cardHand.getScore();
    }

    public List<Card> getCards() {
        return cardHand.getCards();
    }

    public void receiveCard(Card card) {
        cardHand.add(card);
    }

    public boolean isBust() {
        return cardHand.isBust();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Participant that = (Participant) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Participant{" +
                "name=" + name +
                ", cardHand=" + cardHand +
                '}';
    }
}
