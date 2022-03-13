package blackjack.domain.user;

import static java.util.stream.Collectors.toList;

import blackjack.domain.Result;
import blackjack.domain.card.Deck;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

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

    public void calculateAllUser() {
        for (User user : users) {
            user.calculate();
        }
    }

    public void setInitCardsPerPlayer(Deck deck) {
        for (User user : users) {
            user.drawInitCards(deck);
        }
    }

    public void drawAdditionalCard(Consumer<User> consumerPlayer, Consumer<User> consumerDealer) {
        List<User> players = getPlayers();

        for (User player : players) {
            consumerPlayer.accept(player);
        }

        consumerDealer.accept(getDealer());
    }

    public void printResult(Consumer<User> consumer) {
        User dealer = getDealer();

        List<User> players = getPlayers();

        consumer.accept(dealer);

        for (User player : players) {
            consumer.accept(player);
        }
    }

    public Map<String, Result> getYield() {
        return Result.getMap(getPlayers(), getDealer());
    }

    public List<User> getPlayers() {
        return users.stream()
                .filter(user -> !user.isDealer())
                .collect(Collectors.toUnmodifiableList());
    }

    public User getDealer() {
        return users.stream()
                .filter(User::isDealer)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("딜러가 업습니다."));
    }
}
