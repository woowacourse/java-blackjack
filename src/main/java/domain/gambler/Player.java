package domain.gambler;

import domain.Cards;
import domain.constant.WinDrawLose;

public class Player extends Gambler {

    public static final int BUST_STANDARD = 21;

    private final Nickname nickname;

    public Player(Nickname nickname, Cards cards) {
        super(cards);
        this.nickname = nickname;
    }

    public WinDrawLose compareTo(int dealerScore) {
        int sum = sumCardScores();
        if (sum > BUST_STANDARD || dealerScore > BUST_STANDARD) {
            return getWinDrawLoseWhenOverBustStandard(sum);
        }
        return getWinDrawLose(dealerScore, sum);
    }

    private WinDrawLose getWinDrawLoseWhenOverBustStandard(int sum) {
        if (sum > BUST_STANDARD) {
            return WinDrawLose.LOSE;
        }
        return WinDrawLose.WIN;
    }

    private WinDrawLose getWinDrawLose(int dealerScore, int sum) {
        if (sum == dealerScore) {
            return WinDrawLose.DRAW;
        }
        if (sum > dealerScore) {
            return WinDrawLose.WIN;
        }
        return WinDrawLose.LOSE;
    }

    public String getNickname() {
        return nickname.getValue();
    }
}
