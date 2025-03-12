package blackjack.domain.result;

import blackjack.domain.GameRule;
import blackjack.domain.gamer.GameParticipant;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public enum GameResult {

    WIN("승"),
    LOSE("패"),
    DRAW("무");

    private final String description;

    GameResult(String description) {
        this.description = description;
    }

    public static GameResult of(GameParticipant hero, GameParticipant villain) {
        int heroScore = hero.calculateSumOfCards();
        int villainScore = villain.calculateSumOfCards();

        if (bothBust(heroScore, villainScore)) {
            return judgeByRule(hero);
        }

        return compareScores(heroScore, villainScore);
    }

    public static Map<GameResult, Integer> count(List<GameResult> results) {
        Map<GameResult, Integer> gameResultToCount = new EnumMap<>(GameResult.class);

        for (GameResult result : GameResult.values()) {
            gameResultToCount.put(result, 0);
        }

        results.forEach(result ->
                gameResultToCount.merge(result, 1, Integer::sum));

        return gameResultToCount;
    }

    public GameResult oppose() {
        if (this == WIN) {
            return LOSE;
        }

        if (this == LOSE) {
            return WIN;
        }

        return DRAW;
    }

    public String getDescription() {
        return description;
    }

    private static boolean bothBust(int heroScore, int villainScore) {
        return GameRule.isBust(heroScore) && GameRule.isBust(villainScore);
    }

    // 동시에 버스트된다면, 딜러의 승리다
    private static GameResult judgeByRule(GameParticipant hero) {
        if (hero.isDealer()) {
            return WIN;
        }
        return LOSE;
    }

    private static GameResult compareScores(int heroScore, int villainScore) {
        heroScore = GameRule.applyBustRule(heroScore);
        villainScore = GameRule.applyBustRule(villainScore);

        if (heroScore > villainScore) {
            return WIN;
        }

        if (heroScore < villainScore) {
            return LOSE;
        }

        return DRAW;
    }
}
