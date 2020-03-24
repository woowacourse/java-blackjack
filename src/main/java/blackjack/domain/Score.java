package blackjack.domain;

import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.Player;

public class Score {
    private static final int MIN = 4;
    private static final int MAX = 30;

    public static int validate(int score) {
        if (score < MIN || score > MAX) {
            throw new IllegalArgumentException("점수가 게임에서 나올 수 없는 점수입니다!");
        }
        return score;
    }

    public static boolean isLessThan(Dealer dealer, Player player) {
        return dealer.score() > player.score();
    }

    public static boolean isEqual(Dealer dealer, Player player) {
        return dealer.score() == player.score();
    }

    public static boolean isGreaterThan(Dealer dealer, Player player) {
        return dealer.score() < player.score();
    }

}
