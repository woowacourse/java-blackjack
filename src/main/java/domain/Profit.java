package domain;

import domain.player.User;

public class Profit {
    private static final double WIN_BY_BLACK_JACK_RATE = 1.5d;
    private static final double LOOSE_RATE = -1.0d;
    public static final double DRAW_BLACK_JACK_PROFIT = 0.0d;

    private Profit() {
    }

    public static double calculateProfit(User targetUser, User User) {
        if (targetUser == null || User == null) {
            throw new NullPointerException("플레이어 또는 딜러를 입력하지 않았습니다.");
        }

        if (targetUser.isBlackJack() && User.isBlackJack()) {
            return DRAW_BLACK_JACK_PROFIT;
        }
        if (targetUser.isBlackJack()) {
            return (WIN_BY_BLACK_JACK_RATE * targetUser.getMoney());
        }
        if (DecisionWinner.compareWinner(targetUser, User)) {
            return targetUser.getMoney();
        }
        return (LOOSE_RATE * targetUser.getMoney());
    }

}
