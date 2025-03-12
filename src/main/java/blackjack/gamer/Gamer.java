package blackjack.gamer;

import blackjack.bettingMachine.BettingMachine;
import blackjack.card.Card;
import blackjack.card.Hand;
import java.util.List;

public abstract class Gamer {

    private final Hand hand;
    protected final BettingMachine bettingMachine;

    public Gamer() {
        this.hand = new Hand();
        this.bettingMachine = new BettingMachine();
    }

    public void receiveCards(List<Card> cards) {
        hand.addCards(cards);
    }

    public void receiveCard(Card card) {
        hand.addCard(card);
    }

    private List<Card> showAllCards() {
        return hand.openAllCards();
    }

    private int sumCards() {
        return hand.sumCards();
    }

    public abstract String getNickName();
}
