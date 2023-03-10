package blackjack.domain.participant;

import blackjack.domain.Money;
import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import java.util.List;

public abstract class Participant {

    private Hand hand;
    private Money money;

    public Participant(final Hand hand, final Money money) {
        this.hand = hand;
        this.money = money;
    }

    public void receiveCard(final Card card) {
        this.hand = this.hand.add(card);
    }

    public void receiveMoney(final Money money) {
        this.money = this.money.add(money);
    }

    public int totalScore() {
        return this.hand.totalScore();
    }

    public void receive(Card card) {
        this.hand = this.hand.add(card);
    }

    public List<Card> getHand() {
        return hand.getHand();
    }

    public int getMoney() {
        return money.getMoney();
    }
}
