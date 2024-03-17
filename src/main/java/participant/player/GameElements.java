package participant.player;

import card.Card;
import card.Cards;
import java.util.List;

public class GameElements {

    private final BetMoney betMoney;
    private final Cards cards;

    public GameElements(List<Card> initCards, BetMoney betMoney) {
        this.betMoney = betMoney;
        this.cards = new Cards(initCards);
    }

    public BetMoney getBetMoney() {
        return betMoney;
    }

    public Cards getCards() {
        return cards;
    }

    public boolean isBustCard() {
        return cards.isBust();
    }

    public void hit(Card card) {
        cards.addCard(card);
    }

    public int getCardsScore() {
        return cards.countMaxScore();
    }

    public boolean isBlackJackCard() {
        return cards.isBlackJack();
    }
}
