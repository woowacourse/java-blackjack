package blackjack.dto;

import blackjack.model.participant.Dealer;
import blackjack.model.participant.Player;
import blackjack.model.result.PlayerResult;
import java.util.ArrayList;
import java.util.List;

public record PlayerResultsDto(List<PlayerResultDto> results) {

    public static PlayerResultsDto of(List<Player> players, Dealer dealer) {
        List<PlayerResultDto> results = new ArrayList<>();

        for (Player player : players) {
            PlayerResult playerResult = dealer.judgePlayerResult(player);
            results.add(new PlayerResultDto(player.getName(), playerResult));
        }

        return new PlayerResultsDto(results);
    }

    public long countOf(PlayerResult target) {
        return results.stream()
                .filter(result -> result.playerResult() == target)
                .count();
    }
}
