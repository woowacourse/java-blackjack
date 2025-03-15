package blackjack.domain.round;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamer;
import blackjack.domain.gamer.Player;
import blackjack.dto.response.ProfitsResponseDto;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ProfitCalculator {

    private static final double NORMAL_WIN_MULTIPLIER = 1;
    private static final double LOSE_MULTIPLIER = -1;
    private static final double TIE_MULTIPLIER = 0;
    private static final double BLACKJACK_WIN_MULTIPLIER = 1.5;

    private final LinkedHashMap<Gamer, Bet> playerBets = new LinkedHashMap<>();

    public void addPlayerBet(Gamer gamer, int amount) {
        playerBets.put(gamer, new Bet(amount));
    }

    public ProfitsResponseDto getProfits(Round round) {
        Map<String, Double> playerProfits = getPlayerProfits(round.getDealer(), round.getPlayers());
        Map<String, Double> dealerProfit = getDealerProfit(round.getDealer(), playerProfits);
        return ProfitsResponseDto.of(dealerProfit, playerProfits);
    }

    private Map<String, Double> getPlayerProfits(Dealer dealer, List<Player> players) {
        Map<String, Double> profits = new LinkedHashMap<>();
        for (Player player : players) {
            double playerProfit = getPlayerProfit(player, dealer);
            profits.put(player.getName(), playerProfit);
        }
        return profits;
    }

    private double getPlayerProfit(Player player, Dealer dealer) {
        Bet playerBet = playerBets.get(player);
        RoundResult roundResult = RoundResult.judgeResult(player, dealer);
        return getProfit(playerBet, roundResult);
    }

    private double getProfit(Bet bet, RoundResult roundResult) {
        if (roundResult == RoundResult.LOSE || roundResult == RoundResult.BUST) {
            return bet.value() * LOSE_MULTIPLIER;
        }
        if (roundResult == RoundResult.TIE) {
            return bet.value() * TIE_MULTIPLIER;
        }
        if (roundResult == RoundResult.BLACKJACK_WIN) {
            return bet.value() * BLACKJACK_WIN_MULTIPLIER;
        }
        return bet.value() * NORMAL_WIN_MULTIPLIER;
    }

    private Map<String, Double> getDealerProfit(Dealer dealer, Map<String, Double> playerProfits) {
        double totalPlayerProfit = playerProfits.values().stream()
                .mapToDouble(Double::doubleValue)
                .sum();
        return new LinkedHashMap<>(Map.ofEntries(
                Map.entry(dealer.getName(), -totalPlayerProfit)
        ));
    }
}
