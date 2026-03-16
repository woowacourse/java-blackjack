package domain;

import domain.participant.Dealer;
import domain.participant.Participants;
import domain.participant.Player;
import domain.participant.Players;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

public class Referee {

    public Profits calculateProfits(Participants participants, BettingBoard bettingBoard) {
        Dealer dealer = participants.getDealer();
        Map<Player, Profit> playerProfits = calculatePlayerProfits(participants.getPlayers(), dealer, bettingBoard);
        return Profits.from(dealer, playerProfits);
    }

    private Map<Player, Profit> calculatePlayerProfits(Players players, Dealer dealer, BettingBoard bettingBoard) {
        Map<Player, Profit> playerProfits = new LinkedHashMap<>();
        for (Player player : players) {
            BigDecimal profitRate = player.calculateProfitRate(dealer);
            Profit profit = bettingBoard.calculateProfit(player, profitRate);
            playerProfits.put(player, profit);
        }
        return playerProfits;
    }
}
