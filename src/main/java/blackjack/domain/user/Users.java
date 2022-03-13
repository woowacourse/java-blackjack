package blackjack.domain.user;

import static java.util.stream.Collectors.toList;

import blackjack.domain.card.Deck;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Users {

    private final List<User> users;

    private Users(List<User> users) {
        this.users = users;
    }

    public static Users of(List<String> playerNames, Dealer dealer) {
        validateDuplication(playerNames);

        List<User> users = playerNames.stream()
                .map(Player::from)
                .collect(toList());

        users.add(dealer);

        return new Users(users);
    }

    private static void validateDuplication(List<String> playerNames) {
        HashSet<String> hashSet = new HashSet<>(playerNames);

        if (hashSet.size() < playerNames.size()) {
            throw new IllegalArgumentException("참여자 이름이 중복되면 안됩니다.");
        }
    }

    public void setInitCardsPerPlayer(Deck deck) {
        for (User user : users) {
            user.drawInitCards(deck);
        }
    }

    public List<User> getPlayers() {
        List<User> players = new ArrayList<>();

        for (User user : users) {
            checkPlayer(players, user);
        }

        return Collections.unmodifiableList(players);
    }

    private void checkPlayer(List<User> players, User user) {
        if (user.isPlayer()) {
            players.add(user);
        }
    }

    public User getDealer() {
        return users.stream()
                .filter(User::isDealer)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("딜러가 업습니다."));
    }

    public void calculateAllUser() {
        for (User user : users) {
            user.calculate();
        }
    }
}
