package blackjack.gamer;

import blackjack.bettingMachine.BettingMachine;
import blackjack.card.Card;
import blackjack.card.Hand;
import java.util.List;

public abstract class Gamer {

    protected final Hand hand;
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

    public int sumCards() {
        return hand.sumCards();
    }

    public abstract List<Card> showInitialCards();

    public abstract String getNickName();
}
