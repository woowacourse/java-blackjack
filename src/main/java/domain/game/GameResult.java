package domain.game;

import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Player;
import domain.participant.Players;
import dto.ParticipantResultInfo;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static domain.game.ProfitCalculator.calculatePlayerProfit;

public class GameResult {
    private final Map<Participant, BigDecimal> participantsProfits = new HashMap<>();

    public GameResult(Players players, Dealer dealer) {
        BigDecimal playersProfitSum = BigDecimal.ZERO;

        for (Player player : players.getPlayers()) {
            BigDecimal profit = calculatePlayerProfit(player, dealer);
            playersProfitSum = playersProfitSum.add(profit);

            participantsProfits.put(player, profit);
        }
        participantsProfits.put(dealer, playersProfitSum.negate());
    }

    public ParticipantResultInfo participantResultInfo(Participant participant) {
        return new ParticipantResultInfo(participant.name(), participantsProfits.get(participant));
    }
}

