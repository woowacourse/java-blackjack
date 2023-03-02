package model.user;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Users {

    private final List<User> users;

    public Users(final List<String> playersName) {
        this.users = createPlayers(playersName);
    }

    private List<User> createPlayers(final List<String> playersName) {
        final List<User> players = new LinkedList<>();
        
        playersName.stream()
                .map(playerName -> new Player(playerName, Hand.create()))
                .forEach(players::add);

        players.add(0, Dealer.getInstance());

        return players;
    }

    public List<Boolean> getFinalResultWithoutDealer() {
        final Dealer dealer = Dealer.getInstance();
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
        if (dealerTotalValue <= user.calculateTotalValue()) {
            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }

    public List<User> getUsers() {
        return users;
    }
}
