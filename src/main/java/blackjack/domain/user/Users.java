package blackjack.domain.user;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toUnmodifiableList;

import blackjack.domain.Result;
import blackjack.domain.card.Deck;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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

    public Map<String, Integer> calculateRevenue() {
        Map<String, Integer> revenue = new LinkedHashMap<>();

        Map<String, Integer> playerRevenue = getPlayerRevenue();

        revenue.put(getDealer().getName(), calculateDealerRevenue(playerRevenue));

        revenue.putAll(playerRevenue);

        return revenue;
    }

    private int calculateDealerRevenue(Map<String, Integer> playerRevenue) {
        return - (playerRevenue.values()
                .stream()
                .mapToInt(value -> value).sum());
    }

    private Map<String, Integer> getPlayerRevenue() {
        Map<Player, Double> result = Result.decideResult(getPlayers(), getDealer());

        return result.keySet()
                .stream()
                .collect(
                        toMap(player -> player.getName(), player -> player.getRevenue(result.get(player)),
                                (e1, e2) -> e1,
                                LinkedHashMap::new)
                );
    }

    public List<User> getPlayers() {
        return users.stream()
                .filter(user -> !user.isDealer())
                .collect(toUnmodifiableList());
    }

    public User getDealer() {
        return users.stream()
                .filter(User::isDealer)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("딜러가 업습니다."));
    }
}
