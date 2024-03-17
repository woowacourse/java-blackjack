package model.result;

import java.math.BigDecimal;
import java.util.List;
import model.betting.Bet;
import model.game.GameResult;
import model.participant.Dealer;
import model.participant.Player;
import model.participant.Players;

public class ParticipantProfits {

    private final ParticipantProfit dealerProfit;
    private final List<ParticipantProfit> playerProfits;

    private ParticipantProfits(ParticipantProfit dealerProfit,
        List<ParticipantProfit> playerProfits) {
        this.dealerProfit = dealerProfit;
        this.playerProfits = playerProfits;
    }

    public static ParticipantProfits of(Players players, Dealer dealer) {
        List<ParticipantProfit> playersProfits = calculateProfits(players, dealer);
        ParticipantProfit dealerProfit = calculateDealerProfit(playersProfits, dealer);
        return new ParticipantProfits(dealerProfit, playersProfits);
    }

    private static List<ParticipantProfit> calculateProfits(Players players, Dealer dealer) {
        return players.getPlayers()
            .stream()
            .map(player -> calculateBetProfit(player, dealer))
            .toList();
    }

    private static ParticipantProfit calculateBetProfit(Player player, Dealer dealer) {
        Bet bet = player.getBet();
        GameResult resultStatus = dealer.judge(player);
        BigDecimal profit = resultStatus.calculateProfit(bet);
        return new ParticipantProfit(player.getName(), profit);
    }

    private static ParticipantProfit calculateDealerProfit(List<ParticipantProfit> playerProfits,
        Dealer dealer) {
        BigDecimal profit = playerProfits.stream()
            .map(ParticipantProfit::getProfit)
            .reduce(BigDecimal.ZERO, BigDecimal::subtract);
        return new ParticipantProfit(dealer.getName(), profit);
    }

    public ParticipantProfit getDealerProfit() {
        return dealerProfit;
    }

    public List<ParticipantProfit> getPlayerProfits() {
        return playerProfits;
    }
}
