package domain.gambler;

import domain.Cards;
import domain.constant.MatchResult;

public class Player extends Gambler {

    public static final int BUST_STANDARD = 21;

    private final Nickname nickname;

    public Player(Nickname nickname, Cards cards) {
        super(cards);
        this.nickname = nickname;
    }

    public MatchResult compareTo(int dealerScore) {
        int sum = sumCardScores();
        if (sum > BUST_STANDARD || dealerScore > BUST_STANDARD) {
            return getMatchResultWhenOverBustStandard(sum);
        }
        return getMatchResult(dealerScore, sum);
    }

    private MatchResult getMatchResultWhenOverBustStandard(int sum) {
        if (sum > BUST_STANDARD) {
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
