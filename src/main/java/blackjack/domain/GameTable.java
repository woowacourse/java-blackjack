package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.money.BettingMoney;
import blackjack.domain.money.Deposit;
import blackjack.domain.money.Money;
import blackjack.domain.user.Name;

public class GameTable {

    private final Deck deck;
    private final Deposit deposit;

    public GameTable(final Deck deck) {
        this.deck = deck;
        this.deposit = new Deposit();
    }

    public void betting(final Name playerName, final BettingMoney money) {
        deposit.betting(playerName, money);
    }

    public Card supplyCard() {
        return deck.draw();
    }

    public Money getProfit(final Name playerName, final Double profitRate) {
        return deposit.getProfit(playerName, profitRate);
    }
}
