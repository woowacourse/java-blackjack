package model.participant;

import model.score.MatchResult;

import java.util.EnumMap;
import java.util.Map;

public class Dealer extends Participant {

    private static final String DEALER_NAME = "딜러";
    private static final int STANDING_CONDITION = 17;

    private final String nickname;
    private final Map<MatchResult, Integer> matchResult;

    private Dealer(String nickname) {
        this.nickname = nickname;
        this.matchResult = new EnumMap<>(MatchResult.class);
        for (MatchResult type : MatchResult.values()) {
            matchResult.put(type, 0);
        }
    }

    public static Dealer newInstance() {
        return new Dealer(DEALER_NAME);
    }

    public boolean isNotUp() {
        return getScore() < STANDING_CONDITION;
    }

    public void updateResult(MatchResult type) {
        matchResult.put(type, matchResult.get(type) + 1);
    }

    public Map<MatchResult, Integer> getMatchResult() {
        return matchResult;
    }

    @Override
    public String getNickname() {
        return nickname;
    }
}
