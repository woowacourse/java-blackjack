package blackjack.domain.participant;

import blackjack.domain.carddeck.Card;
import java.util.List;

public class Player {

    private final Name name;
    private final Hand hand;

    public Player(Name name) {
        this.name = name;
        this.hand = new Hand();
    }

    public void receiveCard(Card card) {
        hand.addCard(card);
    }

    public int getTotalScore() {
        return hand.totalScore();
    }

    public String getName() {
        return this.name.getValue();
    }

    public List<Card> getHand() {
        return hand.toList();
    }
}
