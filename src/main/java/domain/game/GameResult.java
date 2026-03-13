package domain.game;

import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Player;
import domain.participant.Players;
import dto.ParticipantResultInfo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class GameResult {
    private final List<ParticipantResultInfo> participantResultInfos = new ArrayList<>();

    public GameResult(Players players, Dealer dealer) {
        BigDecimal playersProfitSum = BigDecimal.ZERO;
        for (Player player : players.getPlayers()) {
            BigDecimal profit = calculatePlayerProfit(player, dealer);
            playersProfitSum = playersProfitSum.add(calculatePlayerProfit(player, dealer));

            participantResultInfos.add(new ParticipantResultInfo(
                    player.name(), profit
            ));
        }

        participantResultInfos.add(new ParticipantResultInfo(dealer.name(), playersProfitSum.negate()));
    }

    public BigDecimal profit(Participant participant) {
        return participantResultInfo(participant).profit();
    }

    public List<ParticipantResultInfo> participantResultInfos() {
        return participantResultInfos;
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

    private ParticipantResultInfo participantResultInfo(Participant participant) {
        return participantResultInfos.stream()
                .filter(participantResultInfo -> participantResultInfo.name().equals(participant.name()))
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }
}
