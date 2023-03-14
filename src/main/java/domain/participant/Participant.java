package domain.participant;

import domain.card.Card;
import domain.state.Hand;
import domain.state.Hit;
import domain.state.Stand;
import domain.state.State;
import java.util.List;

public class Participant {

    private final Name name;
    protected State state;

    public Participant(final Name name, final List<Card> cards) {
        this.name = name;
        this.state = new Hit(new Hand(cards));
    }

    public void receiveCard(Card card) {
        this.state = this.state.drawCard(card);
    }

    public int calculateScore() {
        return this.state.calculateScore().getValue();
    }

    public void convertStand() {
        if (!this.state.isBust() && !this.state.isBlackJack()) {
            this.state = new Stand(this.state.getHand());
        }
    }

    public String getName() {
        return name.getName();
    }

    public List<Card> getCards() {
        return this.state.getHand().getCards();
    }
}
