package domain.game;

import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Player;
import domain.participant.Players;
import dto.ParticipantResultInfo;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class GameResult {
    private final Map<Participant, BigDecimal> participantsProfits = new HashMap<>();

    public GameResult(Players players, Dealer dealer) {
        BigDecimal playersProfitSum = BigDecimal.ZERO;

        for (Player player : players.getPlayers()) {
            BigDecimal profit = calculatePlayerProfit(player, dealer);
            playersProfitSum = playersProfitSum.add(profit);

            participantsProfits.put(player, player.betAmount());
        }
        participantsProfits.put(dealer, playersProfitSum.negate());
    }

    public ParticipantResultInfo participantResultInfo(Participant participant) {
        return new ParticipantResultInfo(participant.name(), participantsProfits.get(participant));
    }

    private BigDecimal calculatePlayerProfit(Player player, Dealer dealer) {
        WinningStatus winningStatus = WinningStatus.of(player, dealer);
        BigDecimal betAmount = player.betAmount();

        if (player.isBlackjack() && winningStatus == WinningStatus.WIN) {
            return betAmount.multiply(BigDecimal.valueOf(1.5));
        }

        if (winningStatus == WinningStatus.WIN) {
            return betAmount;
        }

        if (winningStatus == WinningStatus.TIE) {
            return BigDecimal.ZERO;
        }

        return betAmount.negate();
    }
}

