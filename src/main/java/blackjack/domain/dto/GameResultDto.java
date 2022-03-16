package blackjack.domain.dto;

import blackjack.domain.Dealer;
import blackjack.domain.GameResult;
import blackjack.domain.Participant;
import blackjack.domain.Player;

import java.util.List;
import java.util.Map;

public class GameResultDto {
    private Participant participant;
    private Map<GameResult, Integer> gameResults;

    private GameResultDto(Participant participant, Map<GameResult, Integer> gameResults) {
        this.participant = participant;
        this.gameResults = gameResults;
    }

    public static GameResultDto ofDealer(Dealer dealer, List<GameResult> gameResults) {
        return new GameResultDto(dealer, GameResult.toGameResultMap(gameResults));
    }

    public static GameResultDto ofPlayer(Player player, GameResult gameResult) {
        return new GameResultDto(player, GameResult.toGameResultMap(gameResult));
    }

    public String  getParticipantName() {
        return participant.getName();
    }

    public Participant getParticipant() {
        return participant;
    }

    public Map<GameResult, Integer> getGameResults() {
        return gameResults;
    }
}
