package domain;

import domain.user.Player;
import domain.user.UserDeck;
import domain.user.Users;

import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Game {
    private final TotalDeck totalDeck;
    private final Users users;

    public Game(TotalDeck totalDeck, Users users) {
        this.totalDeck = totalDeck;
        this.users = users;
        users.stream()
                .forEach(user -> {
                    user.addCard(totalDeck.getNewCard());
                    user.addCard(totalDeck.getNewCard());
                });
        //TODO
    }

    public void doOrDie(String command) {
        if ("y".equals(command)) {
            users.addCardOfCurrentUser(totalDeck.getNewCard());
        }
        if ("n".equals(command)) {
            users.nextUser();
        }
    }

    public UserDeck showCurrentUserDeck() {
        return users.showCurrentUserDeck();
    }

    public boolean continueInput() {
        return users.isCurrentUserPlayer();
    }

    public void addDealerCardInCondition() {
        if (users.getDealerCardSum() <= 16)
            users.addDealerCard(totalDeck.getNewCard());
    }

    public Map<Player, Result> generatePlayerResults() {
        return users.getPlayers().stream()
                .collect(Collectors.toMap(
                        player -> player,
                        player -> users.generatePlayerResult(player)
                ));
    }

    public Map<Result, Integer> generateDealerResult() {
        Map<Player, Result> playerResult = generatePlayerResults();

        Map<Result, Integer> dealerResult = new EnumMap<>(Result.class);
        int loseCount = (int) playerResult.values()
                .stream()
                .filter(result -> result == Result.WIN)
                .count();
        dealerResult.put(Result.LOSE, loseCount);
        dealerResult.put(Result.WIN, playerResult.size() - loseCount);
        return dealerResult;
    }

}
