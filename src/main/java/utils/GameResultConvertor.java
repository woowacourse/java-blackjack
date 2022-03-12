package utils;

import domain.GameResult;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class GameResultConvertor {
    public static Map<String, List<String>> convertToString(final Map<String, List<GameResult>> dealerGameResult,
                                                            final Map<String, GameResult> playersGameResult) {
        final Map<String, List<String>> result = new LinkedHashMap<>();
        convertDealerGameResult(dealerGameResult, result);
        convertPlayerGameResult(playersGameResult, result);
        return result;
    }

    private static void convertDealerGameResult(final Map<String, List<GameResult>> dealerGameResult,
                                                final Map<String, List<String>> result) {
        for (Entry<String, List<GameResult>> entry : dealerGameResult.entrySet()) {
            result.put(entry.getKey(), convertToString(entry.getValue()));
        }
    }

    private static List<String> convertToString(final List<GameResult> gameResults) {
        final List<String> mappedGameResults = new ArrayList<>();
        final List<String> parsedGameResults = new ArrayList<>();
        for (GameResult gameResult : gameResults) {
            mappedGameResults.add(GameResultMapper.get(gameResult));
        }

        append(mappedGameResults, "승", parsedGameResults);
        append(mappedGameResults, "패", parsedGameResults);
        append(mappedGameResults, "무,", parsedGameResults);

        return parsedGameResults;
    }

    private static void convertPlayerGameResult(final Map<String, GameResult> playersGameResult,
                                                final Map<String, List<String>> result) {
        for (Entry<String, GameResult> entry : playersGameResult.entrySet()) {
            result.put(entry.getKey(), convertToString(entry.getValue()));
        }
    }

    private static List<String> convertToString(final GameResult gameResult) {
        return Collections.singletonList(GameResultMapper.get(gameResult));
    }

    private static void append(final List<String> candidates,
                               final String standard,
                               final List<String> parsedGameResults) {
        if (candidates.contains(standard)) {
            final int count = (int) candidates.stream().filter(string -> string.equals(standard)).count();
            final StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(count).append(standard);
            parsedGameResults.add(stringBuilder.toString());
        }
    }

    private GameResultConvertor() {
    }

    private static class GameResultMapper {
        private static final Map<GameResult, String> GAME_RESULT_MAPPER = new EnumMap<>(GameResult.class);

        static {
            GAME_RESULT_MAPPER.put(GameResult.WIN, "승");
            GAME_RESULT_MAPPER.put(GameResult.LOSE, "패");
            GAME_RESULT_MAPPER.put(GameResult.DRAW, "무");
        }

        static String get(GameResult gameResult) {
            return GAME_RESULT_MAPPER.get(gameResult);
        }

        private GameResultMapper() {
        }
    }
}
