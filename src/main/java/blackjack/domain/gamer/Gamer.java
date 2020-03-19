package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import blackjack.domain.rule.Score;

import java.util.List;
import java.util.Objects;

public abstract class Gamer {

    private String name;
    protected Hand hand = new Hand();

    public Gamer(String name) {
        this.name = Objects.requireNonNull(name);
    }

    public void draw(Card card) {
        hand.add(card);
    }

    public boolean isBusted() {
        return handScore().isBusted();
    }

    public Score handScore() {
        return hand.getScore();
    }

    public abstract boolean canDrawCard();

    public String getName() {
        return name;
    }

    public List<Card> getHand() {
        return hand.getCardStatus();
    }

    public int getHandScore() {
        return handScore().getNumber();
    }
}