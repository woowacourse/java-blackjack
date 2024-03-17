package participant.player;

import card.Card;
import card.Hand;
import java.util.List;

public class GameElements {

    private final BetMoney betMoney;
    private final Hand hand;

    public GameElements(List<Card> initCards, BetMoney betMoney) {
        this.betMoney = betMoney;
        this.hand = new Hand(initCards);
    }

    public BetMoney getBetMoney() {
        return betMoney;
    }

    public Hand getCards() {
        return hand;
    }

    public boolean isBustCard() {
        return hand.isBust();
    }

    public void hit(Card card) {
        hand.addCard(card);
    }

    public int getCardsScore() {
        return hand.countMaxScore();
    }

    public boolean isBlackJackCard() {
        return hand.isBlackJack();
    }
}
