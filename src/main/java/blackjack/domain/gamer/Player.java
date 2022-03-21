package blackjack.domain.gamer;

import static blackjack.domain.card.CardGroup.BLACKJACK_NUMBER;

import blackjack.domain.BettingMoney;
import blackjack.domain.card.Card;
import blackjack.domain.result.Match;
import java.util.List;

public class Player {

    private final Gamer gamer;
    private final BettingMoney bettingMoney;

    public Player(String name, BettingMoney bettingMoney) {
        this.gamer = new Gamer(name);
        this.bettingMoney = bettingMoney;
    }

    public Player(String name, List<Card> cards, BettingMoney bettingMoney) {
        this.gamer = new Gamer(name, cards);
        this.bettingMoney = bettingMoney;
    }

    public static Player of(String playerName, BettingMoney bettingMoney) {
        return new Player(playerName, bettingMoney);
    }

    public void addCard(Card card) {
        gamer.addCard(card);
    }

    public Match compareCardsSumTo(int anotherCardsSum) {
        if (gamer.isBust()) {
            return Match.LOSE;
        }

        if (isBust(anotherCardsSum)) {
            return Match.WIN;
        }

        return Match.of(Integer.compare(gamer.getScore(), anotherCardsSum));
    }

    private boolean isBust(int sum) {
        return sum > BLACKJACK_NUMBER;
    }

    public boolean isNotBust() {
        return gamer.isNotBust();
    }

    public boolean isBlackJack() {
        return gamer.isBlackJack();
    }

    public BettingMoney getBettingMoney() {
        return bettingMoney;
    }

    public String getName() {
        return gamer.getName();
    }

    public List<Card> getCards() {
        return gamer.getCards();
    }

    public int getScore() {
        return gamer.getScore();
    }
}
