package model.bating;

import model.score.MatchResult;

public class BatingManager {

    public static int batingBenefit(MatchResult result, Money money) {
        int batingMoney = money.getValue();
        switch (result) {
            case WIN:
                return batingMoney;
            case BLACKJACK:
                return (int) (batingMoney * 1.5);
            case LOSE:
            case BUST:
                return -1 * batingMoney;
            case PUSH:
                return 0;
            default:
                throw new IllegalStateException("존재할 수 없는 결과 입니다.");
        }
    }
}
