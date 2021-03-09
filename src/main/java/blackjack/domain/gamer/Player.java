package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hands;
import java.util.List;

public class Player {

    private final String name;
    private final int money;
    private final Hands hands;

    public Player(String name, int money,  Hands hands) {
        this.name = name;
        this.money = money;
        this.hands = hands;
    }

    public int winProfit() {
        return money;
    }

    public int loseProfit() {
        return -money;
    }

    public List<Card> showHands() {
        return hands.toList();
    }

    public String getName() {
        return name;
    }

    public int getPoint() {
        return hands.calculate();
    }

    public boolean hasBlackjack() {
        return hands.isBlackjack();
    }

    public void receiveCard(Card card) {
        hands.addCard(card);
    }

    public List<Card> showOpenHands() {
        return hands.getCardOf(2);
    }
}
