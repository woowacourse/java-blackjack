package domain.participant;

import domain.constant.GameResult;
import dto.PlayerGameResultDto;

import java.util.List;
import java.util.Map;

public record GameResults(List<GameResult> dealerGameResult, Map<Player, GameResult> playerGameResults) {
    public List<PlayerGameResultDto> getPlayerGameResultDto() {
        return playerGameResults.entrySet()
                .stream()
                .map(playerGameResult ->
                        new PlayerGameResultDto(
                                playerGameResult.getKey().getPlayerName(),
                                playerGameResult.getValue()))
                .toList();
    }
}
