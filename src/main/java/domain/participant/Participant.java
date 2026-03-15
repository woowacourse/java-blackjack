package domain.participant;

import domain.card.Card;
import domain.strategy.DrawStrategy;
import java.util.List;

public abstract class Participant {
    private final Name name;
    private final Hand hand;
    private final DrawStrategy drawStrategy;

    public Participant(String name, DrawStrategy drawStrategy) {
        this.name = new Name(name);
        this.hand = new Hand();
        this.drawStrategy = drawStrategy;
    }

    public void receiveInitialCards(List<Card> cards) {
        cards.forEach(card -> receive(card));
    }

    public void receive(Card card) {
        hand.add(card);
    }

    public int handSize() {
        return hand.size();
    }

    public int score() {
        return hand.score();
    }

    public String name() {
        return name.name();
    }

    protected Card firstCard() {
        return hand.firstCard();
    }

    public List<Card> getAllCards() {
        return hand.getAllCards();
    }

    public boolean canDraw() {
        int score = hand.score();
        return drawStrategy.canDraw(score);
    }
}
