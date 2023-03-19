package blackjack.dto;

import blackjack.domain.betting.Betting;
import blackjack.domain.game.GameResult;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;

import java.util.List;

public class BettingResultDto {

    private final String dealerName;
    private final Betting dealerProfit;
    private final List<Participant> players;
    private final GameResult gameResult;

    private BettingResultDto(final String dealerName,
                             final Betting dealerProfit,
                             final List<Participant> players,
                             final GameResult gameResult) {
        this.dealerName = dealerName;
        this.dealerProfit = dealerProfit;
        this.players = players;
        this.gameResult = gameResult;
    }

    public static BettingResultDto of(final Participants participants, final GameResult gameResult) {
        return new BettingResultDto(
                participants.getDealer().getName(),
                gameResult.getDealerResult(),
                participants.getPlayers(),
                gameResult
        );
    }

    public String dealerName() {
        return dealerName;
    }

    public Betting dealerProfit() {
        return dealerProfit;
    }

    public List<Participant> players() {
        return players;
    }

    public Betting getPlayerResult(final Participant player) {
        return gameResult.getPlayerResult(player);
    }
}
