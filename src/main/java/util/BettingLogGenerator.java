package util;

import domain.game.Money;
import domain.player.User;
import domain.player.Users;
import view.InputView;

public class BettingLogGenerator {
    private BettingLogGenerator() {
    }

    public static void calculate(final Users users) {
        for (User user : users) {
            String bettingMoney = InputView.inputBettingMoney(user);
            user.addBettingMoney(new Money(bettingMoney));
        }
    }
}
