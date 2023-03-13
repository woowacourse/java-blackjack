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

    public Map<String, Integer> toPlayerProfit(Players players, Dealer dealer) {
        Map<String, Integer> result = new LinkedHashMap<>();
        Map<Player, GameResult> playerProfits = players.toResults(dealer);

        for (final Player player : playerProfits.keySet()) {
            GameResult gameResult = playerProfits.get(player);
            Money money = playerMoney.get(player);
            Money profit = gameResult.getProfit(money);
            result.put(player.getName(), profit.getValue());
        }

        return result;
    }

    public Map<String, Integer> toDealerProfit(Players players, Dealer dealer) {
        Map<String, Integer> result = new LinkedHashMap<>();
        Map<String, Integer> playersProfit = toPlayerProfit(players, dealer);

        Integer playersProfitSum = playersProfit.values().stream()
                .reduce(Integer::sum)
                .orElseThrow(() -> new IllegalArgumentException("사용자의 금액이 존재하지 않습니다."));
        Money money = new Money(playersProfitSum);

        Money dealerProfit = money.multiple(new Money(-1));
        result.put(dealer.getName(), dealerProfit.getValue());

        return result;
    }

}
