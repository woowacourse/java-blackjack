package dto;

import domain.Dealer;
import domain.Player;
import domain.WinningStatus;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public record FinalResultDto(long dealerWinCount, long dealerLoseCount, List<PlayerResultDto> playerResults) {

    public static FinalResultDto from(Map<String, WinningStatus> playerResults) {
        long dealerWinCount = playerResults.values().stream()
                .filter(v -> v == WinningStatus.LOSE)
                .count();

        long dealerLoseCount = playerResults.values().stream()
                .filter(v -> v == WinningStatus.WIN)
                .count();

        List<PlayerResultDto> transformedPlayerResults = playerResults.entrySet().stream()
                .map(entry -> new PlayerResultDto(entry.getKey(), entry.getValue().getDescription()))
                .toList();
        
        return new FinalResultDto(dealerWinCount, dealerLoseCount, transformedPlayerResults);
    }

    public static FinalResultDto of(Dealer dealer, List<Player> players) {
        Map<String, WinningStatus> playerResults = createPlayerResults(dealer, players);
        return FinalResultDto.from(playerResults);
    }

    private static Map<String, WinningStatus> createPlayerResults(Dealer dealer, List<Player> players) {
        return players.stream()
                .collect(Collectors.toMap(
                        Player::getName,
                        player -> calculateWinningStatus(dealer, player),
                        (x, y) -> y,
                        LinkedHashMap::new)
                );
    }

    private static WinningStatus calculateWinningStatus(Dealer dealer, Player player) {
        if (player.isBust()) {
            return WinningStatus.LOSE;
        }
        if (dealer.isBust()) {
            return WinningStatus.WIN;
        }
        if (dealer.getScore() > player.getScore()) {
            return WinningStatus.LOSE;
        }
        if (dealer.getScore() == player.getScore()) {
            return WinningStatus.DRAW;
        }
        return WinningStatus.WIN;
    }
}
