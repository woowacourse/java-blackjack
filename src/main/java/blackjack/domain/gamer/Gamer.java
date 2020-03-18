package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import blackjack.domain.rule.Score;

import java.util.List;

public abstract class Gamer {

    protected Hand hand = new Hand();

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

    public abstract String getName();

    public List<Card> getHand() {
        return hand.getCardStatus();
    }

    public int getHandScore() {
        return handScore().getNumber();
    }
}