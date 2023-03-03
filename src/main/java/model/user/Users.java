package model.user;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Users {

    private static final int BURST_NUMBER = 21;

    private final List<User> users;

    private Users(final List<String> playersName, final Dealer dealer) {
        this.users = createPlayers(playersName, dealer);
    }

    public static Users from(final List<String> playersName) {
        return new Users(playersName, Dealer.getInstance());
    }

    public static Users of(final List<String> playersName, final Dealer dealer) {
        return new Users(playersName, dealer);
    }

    private List<User> createPlayers(final List<String> playersName, final Dealer dealer) {
        final List<User> players = new LinkedList<>();

        playersName.stream()
                .map(playerName -> new Player(playerName, Hand.create()))
                .forEach(players::add);

        players.add(0, dealer);

        return players;
    }

    public List<Boolean> getFinalResult(final User dealer) {
        final int dealerTotalValue = dealer.calculateTotalValue();

        return createFinalResultWithoutDealer(dealerTotalValue);
    }

    private List<Boolean> createFinalResultWithoutDealer(final int dealerTotalValue) {
        final List<Boolean> results = new ArrayList<>();

        for (int player = 1, userSize = users.size(); player < userSize; player++) {
            results.add(judgeResult(dealerTotalValue, users.get(player)));
        }

        return results;
    }

    private boolean judgeResult(final int dealerTotalValue, final User user) {
        final int userTotalValue = user.calculateTotalValue();

        if (dealerTotalValue > BURST_NUMBER || userTotalValue > BURST_NUMBER) {
            return judgeOverBurst(dealerTotalValue, userTotalValue);
        }

        return judgeUnderBurst(dealerTotalValue, userTotalValue);
    }

    private static boolean judgeOverBurst(final int dealerTotalValue, final int userTotalValue) {
        if (userTotalValue > BURST_NUMBER && dealerTotalValue > BURST_NUMBER) {
            return Boolean.TRUE;
        }

        if (userTotalValue > BURST_NUMBER) {
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    private static Boolean judgeUnderBurst(final int dealerTotalValue, final int userTotalValue) {
        if (dealerTotalValue <= userTotalValue) {
            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }

    public List<User> getUsers() {
        return users;
    }
}
