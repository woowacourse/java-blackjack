package blackjack.domain.participant;

import blackjack.domain.Deck;
import blackjack.domain.Hand;
import java.util.ArrayList;

public abstract class BlackJackParticipant {
    private final Hand hand;
    private boolean hit;

    public BlackJackParticipant() {
        this.hand = new Hand(new ArrayList<>());
        this.hit = true;
    }

    abstract public void drawCard(Deck deck);

    public int getScore() {
        return hand.getScore();
    }

    public Hand getHand() {
        return hand;
    }

    public boolean isContinue() {
        return hit;
    }

    protected void cannotDraw() { this.hit = false;}
}
