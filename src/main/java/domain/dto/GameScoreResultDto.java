package domain.dto;

import domain.participant.Dealer;
import domain.participant.Players;

import java.util.ArrayList;
import java.util.List;

public class GameScoreResultDto {
    String playerName;
    List<String> hand;
    int score;

    private GameScoreResultDto(String playerName, List<String> hand, int result) {
        this.playerName = playerName;
        this.hand = hand;
        this.score = result;
    }

    public static List<GameScoreResultDto> from(Players players) {
        return createPlayerScoreResults(players);
    }

    public static List<GameScoreResultDto> createGameScoreResults(Dealer dealer, Players players) {
        List<GameScoreResultDto> results = new ArrayList<>();
        results.add(createDealerScoreResult(dealer));
        results.addAll(createPlayerScoreResults(players));

        return results;
    }

    private static List<GameScoreResultDto> createPlayerScoreResults(Players players) {
        return players.getAll().stream()
                .map(player -> new GameScoreResultDto(
                        player.getName(),
                        player.showHand(),
                        player.getScore())
                ).toList();
    }

    private static GameScoreResultDto createDealerScoreResult(Dealer dealer) {
        return new GameScoreResultDto(dealer.getName(), dealer.showHand(), dealer.getScore());
    }

    public String getPlayerName() {
        return playerName;
    }

    public List<String> getHand() {
        return hand;
    }

    public int getScore() {
        return score;
    }
}
