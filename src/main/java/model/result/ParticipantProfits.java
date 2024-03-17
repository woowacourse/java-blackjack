package model.result;

import java.util.List;
import model.betting.Bet;
import model.betting.PlayerBets;
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

    public static ParticipantProfits of(Players players, Dealer dealer, PlayerBets bets) {
        List<ParticipantProfit> playersProfits = calculateProfits(players, dealer, bets);
        int dealerProfit = calculateDealerProfit(playersProfits);
        return new ParticipantProfits(new ParticipantProfit(dealer.getName(), dealerProfit),
            playersProfits);
    }

    private static List<ParticipantProfit> calculateProfits(Players players, Dealer dealer, PlayerBets bets) {
        return players.getPlayers()
            .stream()
            .map(player -> calculateBetProfit(player, dealer, bets))
            .toList();
    }

    private static ParticipantProfit calculateBetProfit(Player player, Dealer dealer,
        PlayerBets bets) {
        Bet bet = bets.findByPlayer(player);
        GameResult resultStatus = dealer.judge(player);
        int profit = resultStatus.calculateProfit(bet);
        return new ParticipantProfit(player.getName(), profit);
    }

    private static int calculateDealerProfit(List<ParticipantProfit> playersBetProfits) {
        return playersBetProfits.stream()
            .mapToInt(ParticipantProfit::getProfit)
            .sum() * -1;
    }

    public ParticipantProfit getDealerProfit() {
        return dealerProfit;
    }

    public List<ParticipantProfit> getPlayerProfits() {
        return playerProfits;
    }
}
