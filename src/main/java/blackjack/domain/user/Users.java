package blackjack.domain.user;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toUnmodifiableList;

import blackjack.domain.card.Deck;
import java.util.HashSet;
import java.util.List;
import java.util.function.Consumer;

public class Users {

    private final List<User> users;

    private Users(List<User> users) {
        this.users = users;
    }

    public static Users of(List<User> users, Dealer dealer) {
        validateDuplication(users);

        users.add(dealer);

        return new Users(users);
    }

    private static void validateDuplication(List<User> users) {
        List<String> playerNames = users.stream()
                .map(player -> player.getName())
                .collect(toList());

        HashSet<String> hashSet = new HashSet<>(playerNames);

        if (hashSet.size() < playerNames.size()) {
            throw new IllegalArgumentException("참여자 이름이 중복되면 안됩니다.");
        }
    }

    public void drawCards(Deck deck) {
        for (User user : users) {
            user.drawCard(deck);
        }
    }

    public void drawAdditionalCard(Consumer<User> consumerPlayer, Consumer<User> consumerDealer) {
        List<Player> players = getPlayers();

        for (Player player : players) {
            consumerPlayer.accept(player);
        }

        consumerDealer.accept(getDealer());
    }

    public List<Player> getPlayers() {
        return users.stream()
                .filter(user -> !user.isDealer())
                .map(player -> (Player) player)
                .collect(toUnmodifiableList());
    }

    public Dealer getDealer() {
        return users.stream()
                .filter(User::isDealer)
                .findFirst()
                .map(dealer -> (Dealer) dealer)
                .orElseThrow(() -> new IllegalArgumentException("딜러가 업습니다."));
    }
}
