package model.result;

import java.math.BigDecimal;
import java.util.List;
import model.betting.Bet;
import model.game.GameResult;
import model.participant.Dealer;
import model.participant.Player;
import model.participant.Players;

public class ParticipantProfits {

    private final ProfitDto dealerProfit;
    private final List<ProfitDto> playerProfits;

    private ParticipantProfits(ProfitDto dealerProfit,
        List<ProfitDto> playerProfits) {
        this.dealerProfit = dealerProfit;
        this.playerProfits = playerProfits;
    }

    public static ParticipantProfits of(Players players, Dealer dealer) {
        List<ProfitDto> playersProfits = calculateProfits(players, dealer);
        ProfitDto dealerProfit = calculateDealerProfit(playersProfits, dealer);
        return new ParticipantProfits(dealerProfit, playersProfits);
    }

    private static List<ProfitDto> calculateProfits(Players players, Dealer dealer) {
        return players.getPlayers()
            .stream()
            .map(player -> calculateBetProfit(player, dealer))
            .toList();
    }

    private static ProfitDto calculateBetProfit(Player player, Dealer dealer) {
        Bet bet = player.getBet();
        GameResult resultStatus = dealer.judge(player);
        BigDecimal profit = resultStatus.calculateProfit(bet);
        return new ProfitDto(player.getName(), profit);
    }

    private static ProfitDto calculateDealerProfit(List<ProfitDto> playerProfits,
        Dealer dealer) {
        BigDecimal profit = playerProfits.stream()
            .map(ProfitDto::profit)
            .reduce(BigDecimal.ZERO, BigDecimal::subtract);
        return new ProfitDto(dealer.getName(), profit);
    }

    public ProfitDto getDealerProfit() {
        return dealerProfit;
    }

    public List<ProfitDto> getPlayerProfits() {
        return playerProfits;
    }
}
