package profit;

import domain.player.User;

public class BlackJackStrategy implements ProfitStrategy {
    private static final double WIN_BY_BLACK_JACK_RATE = 1.5d;
    private static final double DRAW_BLACK_JACK_PROFIT = 0.0d;

    @Override
    public double calculateProfitStrategy(User targetUser, User user) {
        if (targetUser == null || user == null) {
            throw new NullPointerException("null 값이 입력되었습니다");
        }

        if (targetUser.isBlackJack() && user.isBlackJack()) {
            return DRAW_BLACK_JACK_PROFIT;
        }
        return (WIN_BY_BLACK_JACK_RATE * targetUser.getMoney());

    }
}
