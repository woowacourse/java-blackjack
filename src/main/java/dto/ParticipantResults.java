package dto;

import java.util.List;
import model.game.ResultStatus;

public class ParticipantResults {

    private final DealerResult dealerResult;
    private final List<PlayerResult> playerResults;

    private ParticipantResults(DealerResult dealerResult, List<PlayerResult> playerResults) {
        this.dealerResult = dealerResult;
        this.playerResults = playerResults;
    }

    public static ParticipantResults from(ParticipantScores participantScores) {
        List<ParticipantScore> playerScores = participantScores.getPlayerScores();
        ParticipantScore dealerScore = participantScores.getDealerScore();

        List<PlayerResult> playerResults = playerScores.stream()
            .map(playerScore -> generatePlayerResult(playerScore, dealerScore))
            .toList();
        DealerResult dealerResult = DealerResult.from(playerResults);
        return new ParticipantResults(dealerResult, playerResults);
    }

    public static PlayerResult generatePlayerResult(ParticipantScore player,
        ParticipantScore dealer) {
        String playerName = player.getCard().getName();
        int playerScore = player.getScore();
        int dealerScore = dealer.getScore();

        if (player.isBurst() && dealer.isBurst() || playerScore == dealerScore) {
            return new PlayerResult(playerName, ResultStatus.PUSH);
        }
        if (player.isBurst() || (dealer.isNotBurst() && dealerScore > playerScore)) {
            return new PlayerResult(playerName, ResultStatus.LOOSE);
        }
        return new PlayerResult(playerName, ResultStatus.WIN);
    }

    public DealerResult getDealerResult() {
        return dealerResult;
    }

    public List<PlayerResult> getPlayerResults() {
        return playerResults;
    }
}
