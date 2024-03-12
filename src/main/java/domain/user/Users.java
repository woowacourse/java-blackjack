package domain.user;

import static domain.game.GameResult.LOSE;
import static domain.game.GameResult.WIN;

import domain.TotalDeck;
import domain.card.Card;
import domain.game.GameResult;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Users {
    private static final int DEALER_INDEX = 0;
    private static final int FIRST_PLAYER_INDEX = 1;
    private final List<User> users;

    public Users(List<Player> players) {
        validateDuplicatedName(players);
        users = new ArrayList<>();
        users.add(DEALER_INDEX, new Dealer());
        users.addAll(players);
    }

    public void setStartCards(TotalDeck totalDeck) {
        for (User user : users) {
            user.addCard(totalDeck.getNewCard());
            user.addCard(totalDeck.getNewCard());
        }
    }

    public boolean isDealerCardAddable() {
        return getDealer().isAddable();
    }

    public void addDealerCard(Card card) {
        getDealer().addCard(card);
    }

    public GameResult generatePlayerResult(Player player) {
        if (player.busted()) {
            return LOSE;
        }
        if (getDealer().busted()) {
            return WIN;
        }
        return GameResult.compare(player.sumUserDeck(), getDealer().sumUserDeck());
    }

    public List<User> getUsers() {
        return Collections.unmodifiableList(users);
    }

    public List<Player> getPlayers() {
        List<User> players = users.subList(FIRST_PLAYER_INDEX, users.size());
        return players.stream()
                .map(user -> (Player) user)
                .collect(Collectors.toList());
    }

    private User getDealer() {
        return users.get(DEALER_INDEX);
    }

    private void validateDuplicatedName(List<Player> players) {
        List<Player> distinctPlayers = new ArrayList<>();
        for (Player player : players) {
            throwExceptionIfContains(distinctPlayers, player);
            distinctPlayers.add(player);
        }
    }

    private static void throwExceptionIfContains(List<Player> distinctPlayers, Player player) {
        if (distinctPlayers.contains(player)) {
            throw new IllegalArgumentException(
                    "중복된 이름은 입력할 수 없습니다: %s".formatted(player.getNameValue()));
        }
    }
}
