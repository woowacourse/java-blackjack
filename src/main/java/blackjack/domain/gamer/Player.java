package blackjack.domain.gamer;

import static blackjack.domain.card.CardGroup.BLACKJACK_NUMBER;

import blackjack.domain.BettingMoney;
import blackjack.domain.card.Card;
import blackjack.domain.result.Match;
import java.util.List;

public class Player extends Gamer{

    private final BettingMoney bettingMoney;

    public Player(String name, BettingMoney bettingMoney) {
        super(name);
        this.bettingMoney = bettingMoney;
    }

    public Player(String name, List<Card> cards, BettingMoney bettingMoney) {
        super(name, cards);
        this.bettingMoney = bettingMoney;
    }

    public static Player of(String playerName, BettingMoney bettingMoney) {
        return new Player(playerName, bettingMoney);
    }

    public Match compareCardsSumTo(int anotherCardsSum) {
        if (isBust()) {
            return Match.LOSE;
        }

        if (isBust(anotherCardsSum)) {
            return Match.WIN;
        }

        return Match.of(Integer.compare(getScore(), anotherCardsSum));
    }

    private boolean isBust(int sum) {
        return sum > BLACKJACK_NUMBER;
    }

    public BettingMoney getBettingMoney() {
        return bettingMoney;
    }
}
