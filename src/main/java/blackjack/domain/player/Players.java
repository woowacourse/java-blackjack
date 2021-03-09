package blackjack.domain.player;

import blackjack.domain.blackjackgame.Money;
import blackjack.domain.card.Deck;
import java.util.List;

public class Players {

    private final List<Player> players;

    public Players(List<Player> players) {
        this.players = players;
    }

    public Money calculateDealerProfit() {
        Money dealerProfit = new Money();
        for (Player player : players) {
            Money playerProfitMoney = player.profit();
            dealerProfit = dealerProfit.add(playerProfitMoney.minus());
        }
        return dealerProfit;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void initialDraw(Deck deck) {
        players.forEach(player -> player.initialDraw(deck));
    }

    public void calculateGameResult(Dealer dealer) {
        players.forEach(player -> player.calculateGameResult(dealer));
    }
}
