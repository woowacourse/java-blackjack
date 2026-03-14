package blackjack.domain.bet;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

public class Bets {

    private final Map<Player, Bet> bets;

    public Bets() {
        bets = new LinkedHashMap<>();
    }

    public void playerBet(Player player, int betAmount) {
        bets.put(player, new Bet(betAmount));
    }

    public Map<Player, Integer> determinePlayerProfits(Dealer dealer) {
        return getBets().stream()
                .collect(Collectors.toMap(
                        Entry::getKey,
                        playerBetEntry -> determinePlayerProfit(playerBetEntry, dealer),
                        (exist, replace) -> exist,
                        LinkedHashMap::new
                ));
    }

    private int determinePlayerProfit(Entry<Player, Bet> playerBet, Dealer dealer) {
        Player player = playerBet.getKey();
        Bet bet = playerBet.getValue();
        ProfitRate profitRate = calculatePlayerProfitRate(player, dealer);
        return bet.calculateProfit(profitRate);
    }

    private ProfitRate calculatePlayerProfitRate(Player player, Dealer dealer) {
        if (player.isBlackjack()) {
            return calculatePlayerBlackjackProfitRate(player, dealer);
        }
        if (player.isBust()) {
            return ProfitRate.LOSE;
        }
        if (dealer.isBust()) {
            return ProfitRate.WIN;
        }
        return calculatePlayerStandProfitRate(player.getScore(), dealer.getScore());
    }

    private ProfitRate calculatePlayerStandProfitRate(int playerScore, int dealerScore) {
        if (dealerScore == playerScore) {
            return ProfitRate.DRAW;
        }
        if (dealerScore < playerScore) {
            return ProfitRate.WIN;
        }
        return ProfitRate.LOSE;
    }

    private ProfitRate calculatePlayerBlackjackProfitRate(Player player, Dealer dealer) {
        if (player.isBlackjack() && dealer.isBlackjack()) {
            return ProfitRate.DRAW;
        }
        return ProfitRate.WIN_BLACKJACK;
    }

    public Set<Entry<Player, Bet>> getBets() {
        return Collections.unmodifiableSet(bets.entrySet());
    }
}
