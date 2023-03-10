package blackjack.domain.game;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

import blackjack.domain.participant.dealer.DealerWinningDto;
import blackjack.domain.participant.player.Player;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class GameResultDto {
    private final List<PlayerWinningDto> playerToResult;
    private final DealerWinningDto dealerResult;

    private GameResultDto(List<PlayerWinningDto> playerToResult, DealerWinningDto dealerResult) {
        this.playerToResult = playerToResult;
        this.dealerResult = dealerResult;
    }

    public static GameResultDto of(Map<Player, WinningResult> playerToResult) {
        List<PlayerWinningDto> playerWinnings = playerToResult.entrySet().stream()
                .map(entry -> new PlayerWinningDto(entry.getKey().getName(), entry.getValue()))
                .collect(Collectors.toList());
        Map<WinningResult, Long> dealerResult = playerToResult.entrySet().stream()
                .collect(groupingBy(Entry::getValue, counting()));
        return new GameResultDto(playerWinnings, new DealerWinningDto(dealerResult));
    }

    public List<PlayerWinningDto> getPlayerToResult() {
        return Collections.unmodifiableList(playerToResult);
    }

    public DealerWinningDto getDealerResult() {
        return dealerResult;
    }
}
