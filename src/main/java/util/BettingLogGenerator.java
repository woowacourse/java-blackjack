package util;

import domain.betting.BettingLog;
import domain.betting.BettingLogs;
import domain.game.Money;
import domain.player.User;
import domain.player.Users;
import view.InputView;

import java.util.ArrayList;
import java.util.List;

public class BettingLogGenerator {
    private BettingLogGenerator() {
    }

    public static BettingLogs create(final Users users) {
        List<BettingLog> bettingLogs = new ArrayList<>();

        addUserBettingLogs(users, bettingLogs);
        return new BettingLogs(bettingLogs);
    }

    private static void addUserBettingLogs(Users users, List<BettingLog> bettingLogs) {
        for (User user : users) {
            String bettingMoney = InputView.inputBettingMoney(user);
            bettingLogs.add(new BettingLog(user.getName(), new Money(bettingMoney)));
        }
    }
}
