package blackjack.domain.game;

import blackjack.domain.money.BetAndProfit;
import blackjack.domain.money.Money;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class ProfitResult {

    // TODO: 불변객체로 만들어야함
    private static final String NOT_FOUND_PLAYER_EXCEPTION_MESSAGE = "존재하지 않는 플레이어입니다.";

    private final Map<Player, BetAndProfit> playerBetAndProfits;

    private ProfitResult(Map<Player, BetAndProfit> playerBetAndProfits, Dealer dealer) {
        calculateBetAndProfitOfPlayers(playerBetAndProfits, dealer);
        this.playerBetAndProfits = playerBetAndProfits;
    }

    public static ProfitResult of(Map<Player, BetAndProfit> playerBetAndProfits, Dealer dealer) {
        return new ProfitResult(playerBetAndProfits, dealer);
    }

    private void calculateBetAndProfitOfPlayers(Map<Player, BetAndProfit> playerBetAndProfits, Dealer dealer) {
        for (Player player : playerBetAndProfits.keySet()) {
            calculateBetAndProfitOfSinglePlayer(playerBetAndProfits, dealer, player);
        }
    }

    private void calculateBetAndProfitOfSinglePlayer(Map<Player, BetAndProfit> playerBetAndProfits, Dealer dealer,
                                                     Player player) {
        if (isPlayerWin(dealer, player)) {
            playerBetAndProfits.get(player).win();
        }
        if (isPlayerWinWithBlackjack(dealer, player)) {
            playerBetAndProfits.get(player).winWithBlackjack();
        }
        if (isPlayerLose(dealer, player)) {
            playerBetAndProfits.get(player).lose();
        }
    }

    private boolean isPlayerWin(Dealer dealer, Player player) {
        return player.compareWith(dealer) == ResultType.WIN;
    }

    private boolean isPlayerWinWithBlackjack(Dealer dealer, Player player) {
        boolean isPlayerWin = player.compareWith(dealer) == ResultType.WIN;
        boolean isPlayerBlackjackButDealerNot = player.isBlackjack() && !dealer.isBlackjack();

        return isPlayerWin && isPlayerBlackjackButDealerNot;
    }

    private boolean isPlayerLose(Dealer dealer, Player player) {
        return player.compareWith(dealer) == ResultType.LOSE;
    }

    public BetAndProfit findBetAndProfitBy(Player player) {
        BetAndProfit found = playerBetAndProfits.get(player);
        return Objects.requireNonNull(found, NOT_FOUND_PLAYER_EXCEPTION_MESSAGE);
    }

    public Money getDealerProfit() {
        int playerProfitTotal = playerBetAndProfits.values()
                .stream().mapToInt(betAndProfit -> betAndProfit.getProfitMoney().getValue())
                .sum();

        return Money.from(-playerProfitTotal);
    }

    public Set<Player> getPlayers() {
        return playerBetAndProfits.keySet();
    }

    @Override
    public String toString() {
        return "ProfitResult{" +
                "playerBetAndProfits=" + playerBetAndProfits +
                '}';
    }
}
