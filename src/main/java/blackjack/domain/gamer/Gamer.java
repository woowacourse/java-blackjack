package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;

import java.util.List;
import java.util.Objects;

public abstract class Gamer {

    private String name;
    protected Hand hand;

    Gamer(String name) {
        this.name = Objects.requireNonNull(name);
        this.hand = new Hand();
    }

    public abstract boolean canDrawCard();

    public void draw(Card card) {
        hand.add(card);
    }

    public boolean isBusted() {
        return hand.isBusted();
    }

    public boolean isBlackJack() {
        return hand.isBlackJack();
    }

    public int handScore() {
        return hand.getScore().getNumber();
    }

    public String getName() {
        return name;
    }

    public List<Card> getHand() {
        return hand.getCards();
    }
}