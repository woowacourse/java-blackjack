package domain;

import static domain.BlackjackRule.BLACKJACK_TARGET_SCORE;

import vo.GameResult;

public class GameJudge {
    public static GameResult judgeResultForUser(User user, Dealer dealer) {
        int userTotalScore = user.getTotalScore();
        boolean isUserBlackjack = user.isBlackjack();
        boolean isDealerBlackjack = dealer.isBlackjack();

        // 유저 버스트
        if (userTotalScore > BLACKJACK_TARGET_SCORE) {
            return GameResult.LOSE;
        }

        // 딜러와 유저 둘 다 블랙잭
        if (isUserBlackjack && isDealerBlackjack) {
            return GameResult.DRAW;
        }

        // 유저만 블랙잭
        if (isUserBlackjack) {
            return GameResult.BLACKJACK;
        }

        // 딜러만 블랙잭
        if (isDealerBlackjack) {
            return GameResult.LOSE_BY_BLACKJACK;
        }

        // 딜러 버스트
        if (dealer.getTotalScore() > BLACKJACK_TARGET_SCORE) {
            return GameResult.WIN;
        }

        // 둘 다 21 이하
        if (userTotalScore > dealer.getTotalScore()) {
            return GameResult.WIN;
        }

        if (userTotalScore == dealer.getTotalScore()) {
            return GameResult.DRAW;
        }

        return GameResult.LOSE;
    }
}
