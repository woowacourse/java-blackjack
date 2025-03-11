package domain.gambler;

import domain.Cards;
import domain.constant.MatchResult;

public class Player extends Gambler {

    public static final int MAX_SCORE = 21;

    private final Nickname nickname;

    public Player(Nickname nickname, Cards cards) {
        super(cards);
        this.nickname = nickname;
    }

    public MatchResult compareTo(int dealerScore) {
        int sum = sumCardScores();
        if (sum > MAX_SCORE || dealerScore > MAX_SCORE) {
            return getMatchResultWhenOverBustStandard(sum);
        }
        return getMatchResult(dealerScore, sum);
    }

    private MatchResult getMatchResultWhenOverBustStandard(int sum) {
        if (sum > MAX_SCORE) {
            return MatchResult.LOSE;
        }
        return MatchResult.WIN;
    }

    private MatchResult getMatchResult(int dealerScore, int sum) {
        if (sum == dealerScore) {
            return MatchResult.DRAW;
        }
        if (sum > dealerScore) {
            return MatchResult.WIN;
        }
        return MatchResult.LOSE;
    }

    public String getNickname() {
        return nickname.getValue();
    }
}
