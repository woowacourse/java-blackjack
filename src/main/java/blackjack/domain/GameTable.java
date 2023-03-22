package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.money.BettingMoney;
import blackjack.domain.money.Deposit;
import blackjack.domain.result.PlayerNameProfitRates;
import blackjack.domain.result.UserNameProfits;
import blackjack.domain.user.PlayerName;

public class GameTable {

    private final Deck deck;
    private final Deposit deposit;

    public GameTable(final Deck deck) {
        this.deck = deck;
        this.deposit = new Deposit();
    }

    public void bet(final PlayerName playerName, final BettingMoney money) {
        deposit.bet(playerName, money);
    }

    public Card supplyCard() {
        return deck.draw();
    }

    public UserNameProfits calculateProfits(final PlayerNameProfitRates playerNameAndProfitRates) {
        return deposit.calculateProfits(playerNameAndProfitRates);
    }
}
