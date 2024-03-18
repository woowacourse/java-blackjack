package blackjack.domain.betting;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

public class GameBettingManager {

    private static final Double WIN_BETTING_BLACKJACK = 1.5;
    private static final Double WIN_BETTING_NORMAL = 1.0;
    private static final Double TIE_BETTING = 0.0;
    private static final Double LOSE_BETTING = -1.0;

    private final Map<Player, Betting> playerBetting = new LinkedHashMap<>();

    public GameBettingManager() {
    }

    public void registerPlayerBetting(Player player, Betting betting) {
        playerBetting.put(player, betting);
    }

    public void calculatePlayerProfit(Dealer dealer, Player player) {
        double profitRate = decideWinner(dealer, player);
        playerBetting.put(player, playerBetting.get(player).calculateBettingMoney(profitRate));
    }

    public BigDecimal getDealerResult() {
        return playerBetting.values().stream()
                .map(Betting::getBettingMoney)
                .map(BigDecimal::negate)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Map<Player, Betting> getPlayersResult() {
        return playerBetting;
    }

    private double decideWinner(Dealer dealer, Player player) {
        if (playerLoses(dealer, player)) {
            return LOSE_BETTING;
        }
        if (playerWinsWithBlackJack(dealer, player)) {
            return WIN_BETTING_BLACKJACK;
        }
        if (playerWinsWithoutBlackJack(dealer, player)) {
            return WIN_BETTING_NORMAL;
        }
        return TIE_BETTING;
    }

    private boolean playerWinsWithBlackJack(Dealer dealer, Player player) {
        return player.isBlackJack() && !dealer.isBlackJack();
    }

    private boolean playerWinsWithoutBlackJack(Dealer dealer, Player player) {
        int dealerScore = dealer.calculateTotalScore();
        int playerScore = player.calculateTotalScore();

        if (player.isBusted()) {
            return false;
        }
        if (dealer.isBusted()) {
            return true;
        }
        return playerScore > dealerScore;
    }

    private boolean playerLoses(Dealer dealer, Player player) {
        int dealerScore = dealer.calculateTotalScore();
        int playerScore = player.calculateTotalScore();

        if (player.isBusted()) {
            return true;
        }
        if (dealer.isBusted()) {
            return false;
        }
        return playerScore < dealerScore;
    }
}
