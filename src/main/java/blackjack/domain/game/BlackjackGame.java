package blackjack.domain.game;

import blackjack.domain.cardpack.CardPack;
import blackjack.domain.cardpack.MasterShuffleStrategy;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.player.Player;
import blackjack.domain.user.player.Players;

import java.util.LinkedHashMap;
import java.util.Map;

public class BlackjackGame {

    private static final int INIT_DRAW_COUNT = 2;

    private final CardPack cardPack;
    private final Map<Player, Money> playerMoney = new LinkedHashMap<>();

    public BlackjackGame() {
        this.cardPack = new CardPack();
        initCardPack();
    }

    BlackjackGame(final CardPack cardPack) {
        this.cardPack = cardPack;
    }

    private void initCardPack() {
        cardPack.shuffle(new MasterShuffleStrategy());
    }

    public void initDraw(final Dealer dealer, final Players players) {
        for (int currentCount = 0; currentCount < INIT_DRAW_COUNT; currentCount++) {
            dealer.drawCard(cardPack);
            playersDraw(players);
        }
    }

    private void playersDraw(final Players players) {
        for (final Player player : players.getPlayers()) {
            player.drawCard(cardPack);
        }
    }

    public void playerDraw(final Player player) {
        player.drawCard(cardPack);
    }

    public void dealerDraw(final Dealer dealer) {
        dealer.drawCard(cardPack);
    }

    public void playerBet(final Player player, final Money money) {
        playerMoney.put(player, money);
    }

    public Map<String, Money> toPlayerProfit(Players players, Dealer dealer) {
        Map<String, Money> result = new LinkedHashMap<>();
        Map<Player, GameResult> playerProfits = players.toResults(dealer);

        for (final Player player : playerProfits.keySet()) {
            GameResult gameResult = playerProfits.get(player);
            Money money = playerMoney.get(player);
            result.put(player.getName(), gameResult.getProfit(money));
        }

        return result;
    }

    public Map<String, Money> toDealerProfit(Players players, Dealer dealer) {
        Map<String, Money> result = new LinkedHashMap<>();
        Map<String, Money> playersProfit = toPlayerProfit(players, dealer);
        Money dealerMoney = new Money(0);

        for (final String playerName : playersProfit.keySet()) {
            Money playerProfit = playersProfit.get(playerName);
            dealerMoney = dealerMoney.add(playerProfit);
        }

        result.put(dealer.getName(), dealerMoney.multiple(new Money(-1)));

        return result;
    }
}
