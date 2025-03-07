package model;

import java.util.HashMap;
import java.util.Map;

public class Dealer extends Participant {

    private static final String DEALER_NAME = "딜러";
    private static final int STANDING_CONDITION = 17;

    private final String nickname;
    private final Map<MatchType, Integer> matchResult;

    private Dealer(String nickname) {
        this.nickname = nickname;
        this.matchResult = new HashMap<>();
    }

    public static Dealer of() {
        return new Dealer(DEALER_NAME);
    }

    public ResultType compareTo(Player player) {
        if (player.isBust()) {
            return ResultType.WIN_LOSE;
        }
        if (isBust()) {
            return ResultType.LOSE_WIN;
        }
        return ResultType.of(score.compareTo(player.score));
    }

    public boolean isNotUp() {
        return score.getValue() < STANDING_CONDITION;
    }

    public void updateResult(MatchType type) {
        matchResult.computeIfAbsent(type, k -> 0);
        matchResult.put(type, matchResult.get(type) + 1);
    }

    public Map<MatchType, Integer> getMatchResult() {
        return matchResult;
    }

    @Override
    public String getNickname() {
        return nickname;
    }
}
