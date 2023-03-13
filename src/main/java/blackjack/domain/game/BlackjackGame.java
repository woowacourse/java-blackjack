package blackjack.domain.game;

import blackjack.domain.money.Money;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.domain.result.Result;
import java.util.List;
import java.util.Map;

public final class BlackjackGame {

    private final Dealer dealer;
    private final Players players;

    public BlackjackGame(final Dealer dealer, final Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public void saveBettingMoney(final Player player, final Money money) {
        this.dealer.saveBettingMoney(player, money);
    }

    public void settingGame() {
        dealer.settingCards(players);
        dealer.settingSelf();
    }

    public boolean canDealerDraw() {
        return dealer.canDraw();
    }

    public void drawDealer() {
        dealer.drawIfLowerOrEquals16();
    }

    public void dealOutMoney() {
        Map<Player, Result> playersResult = players.compareHandTo(dealer.getHand());
        Map<Player, Money> exchanges = dealer.exchange(playersResult);

        players.dealOutMoney(exchanges);
        dealer.receiveMoney(settle(dealer.totalBettingMoney(), exchanges));
    }

    private Money settle(final Money totalBettingMoney, final Map<Player, Money> exchanges) {
        Money totalExchangedMoney = dealer.totalExchangedMoney(exchanges);

        return totalBettingMoney.subtract(totalExchangedMoney);
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Players getPlayers() {
        return players;
    }

    public List<Player> getPlayersValues() {
        return players.getPlayers();
    }
}
