package domain;

import domain.participant.Player;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import view.InputView;

public enum MatchResult {
    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private static final int BLACKJACK_NUMBER = 21;

    private final String value;

    MatchResult(final String value) {
        this.value = value;
    }

    public static MatchResult calculateWinner(final int dealerSum, final int playerSum) {
        if (dealerSum > playerSum || playerSum > BLACKJACK_NUMBER) {
            return LOSE;
        }
        if (dealerSum < playerSum) {
            return WIN;
        }
        return DRAW;
    }

    public static LinkedHashMap<MatchResult, Integer> calculateDealerResult(Map<Player, MatchResult> playerMatchResult) {
        int winningCount = (int) playerMatchResult.values().stream().filter(matchResult -> matchResult == LOSE).count();
        int losingCount = (int) playerMatchResult.values().stream().filter(matchResult -> matchResult == WIN).count();
        int drawingCount = (int) playerMatchResult.values().stream().filter(matchResult -> matchResult == DRAW).count();
        return new LinkedHashMap<>(Map.of(WIN, winningCount, DRAW, drawingCount, LOSE, losingCount));
    }

    public String getValue() {
        return value;
    }
}
