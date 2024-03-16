package blackjack.domain.player;

import static java.util.stream.Collectors.toMap;

import blackjack.domain.bet.BetLeverage;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Hands;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class Users {

    private final List<User> users;

    private Users(final List<User> users) {
        this.users = users;
    }

    public static Users of(final PlayerNames playerNames, final Predicate<UserName> userWantToHit) {
        final List<User> users = new ArrayList<>();
        playerNames.getNames().forEach(name -> users.add(new Player(name, userWantToHit)));

        users.add(new Dealer());

        return new Users(users);
    }

    public void drawStartCard(final Deck deck) {
        for (final User user : users) {
            drawStartCard(deck, user);
        }
    }

    private void drawStartCard(final Deck deck, final User user) {
        while (user.isInitState()) {
            user.playTurn(deck);
        }
    }

    public boolean isAllPlayerBust() {
        final List<User> players = findPlayers();

        return players.stream()
                .allMatch(User::isBustState);
    }

    public Map<UserName, BetLeverage> getPlayersBetLeverage() {
        final List<User> players = findPlayers();
        final User dealer = findDealer();

        return players.stream()
                .collect(toMap(User::getUserName,
                        player -> BetLeverage.of(player.getState(), dealer.getState()),
                        (v1, v2) -> v1,
                        LinkedHashMap::new));
    }

    public Map<UserName, Hands> getPlayersOpenedHands() {
        return users.stream()
                .filter(User::isPlayer)
                .collect(toMap(User::getUserName,
                        User::getOpenedHands,
                        (v1, v2) -> v1,
                        LinkedHashMap::new));
    }

    public Hands getDealerOpenedHands() {
        final User dealer = findDealer();
        return dealer.getOpenedHands();
    }

    public Hands getDealerHands() {
        final User dealer = findDealer();
        return dealer.getHands();
    }

    private List<User> findPlayers() {
        return users.stream()
                .filter(User::isPlayer)
                .toList();
    }

    private User findDealer() {
        return users.stream()
                .filter(User::isDealer)
                .findAny()
                .orElseThrow(() -> new IllegalStateException("딜러가 없습니다."));
    }

    public List<User> getUsers() {
        return Collections.unmodifiableList(users);
    }
}
