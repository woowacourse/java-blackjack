package utils;

import domain.GameResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameResultConvertor {

    public static String convertToString(final GameResult gameResult) {
        return GameResultMapper.mapToString(gameResult);
    }

    public static String convertToString(final List<String> results) {
        return String.join(" ", GameResultMapper.convertToCountWithString(results));
    }

    private GameResultConvertor() {
    }

    private enum GameResultMapper {
        WIN(GameResult.WIN, "승"),
        LOSE(GameResult.LOSE, "패"),
        DRAW(GameResult.DRAW, "무"),
        ;

        private final GameResult gameResult;
        private final String name;

        GameResultMapper(final GameResult gameResult, final String name) {
            this.gameResult = gameResult;
            this.name = name;
        }

        public static String mapToString(final GameResult other) {
            return Arrays.stream(GameResultMapper.values())
                    .filter(gameResult -> gameResult.gameResult == other)
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게임결과 입니다."))
                    .name;
        }

        public static List<String> convertToCountWithString(final List<String> results) {
            final List<String> convertedResults = new ArrayList<>();
            for (final GameResultMapper value : values()) {
                addResult(results, convertedResults, value.name);
            }
            return convertedResults;
        }

        private static void addResult(final List<String> origin, final List<String> convertedResults, final String name) {
            if (origin.contains(name)) {
                final long matchingCount = origin.stream().filter(name::equals).count();
                convertedResults.add(matchingCount + name);
            }
        }
    }
}
