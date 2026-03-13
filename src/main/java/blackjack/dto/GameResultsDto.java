package blackjack.dto;

import blackjack.domain.participant.Player;
import blackjack.domain.result.GameResult;
import blackjack.domain.result.GameResults;

import java.util.List;
import java.util.Map;

public record GameResultsDto(String dealerResultText, List<PlayerResultDto> playerResults) {

    private static final String WIN_DISPLAY_TEXT = "승";
    private static final String LOSE_DISPLAY_TEXT = "패";
    private static final String DRAW_DISPLAY_TEXT = "무";
    private static final String WIN_LABEL = "승 ";
    private static final String DRAW_LABEL = "무 ";
    private static final String LOSE_LABEL = "패";

    public record PlayerResultDto(String name, String resultText) {
    }

    public GameResultsDto(GameResults gameResults) {
        this(buildDealerText(gameResults.dealerResult()),
                buildPlayerResults(gameResults.playerResults()));
    }

    private static String buildDealerText(Map<GameResult, Integer> dealerResults) {
        StringBuilder sb = new StringBuilder();
        appendIfExists(sb, dealerResults, GameResult.WIN, WIN_LABEL);
        appendIfExists(sb, dealerResults, GameResult.DRAW, DRAW_LABEL);
        appendIfExists(sb, dealerResults, GameResult.LOSE, LOSE_LABEL);
        return sb.toString().trim();
    }

    private static void appendIfExists(StringBuilder sb, Map<GameResult, Integer> results,
                                       GameResult result, String label) {
        if (results.containsKey(result)) {
            sb.append(results.get(result)).append(label);
        }
    }

    private static List<PlayerResultDto> buildPlayerResults(Map<Player, GameResult> results) {
        return results.entrySet().stream()
                .map(e -> new PlayerResultDto(e.getKey().getName(), toDisplayText(e.getValue())))
                .toList();
    }

    private static String toDisplayText(GameResult result) {
        if (result == GameResult.WIN || result == GameResult.BLACKJACK) {
            return WIN_DISPLAY_TEXT;
        }
        if (result == GameResult.LOSE) {
            return LOSE_DISPLAY_TEXT;
        }
        return DRAW_DISPLAY_TEXT;
    }
}
