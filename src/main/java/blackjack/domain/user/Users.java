package blackjack.domain.user;

import blackjack.domain.GameResult;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardGroup;
import blackjack.domain.card.Deck;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Users {

    private static final String NOT_CONTAIN_DEALER = "Users에 Dealer 객체가 없습니다.";
    private static final String NOT_CONTAIN_USER_BY_NAME = "해당 이름의 유저를 찾을 수 없습니다.";
    private final List<User> users;

    public Users(List<String> playerNames, Deck deck) {
        List<User> users = playerNames.stream()
                .map(name -> new Player(name, initCardGroup(deck)))
                .collect(Collectors.toList());
        users.add(new Dealer(initCardGroup(deck)));
        this.users = List.copyOf(users);
    }

    private CardGroup initCardGroup(Deck deck) {
        return new CardGroup(deck.draw(), deck.draw());
    }

    public Map<String, List<Card>> getHandholdingCards() {
        return users.stream()
                .collect(Collectors.toUnmodifiableMap(User::getName, User::getHandholdingCards));
    }

    public Map<String, List<Card>> getInitialHoldingStatus() {
        return users.stream()
                .collect(Collectors.toUnmodifiableMap(User::getName, User::getInitialHoldingCards));
    }

    public boolean isDealerOverDrawLimit() {
        return getDealer().isOverDraw();
    }

    private Dealer getDealer() {
        return users.stream()
                .filter(user -> user instanceof Dealer)
                .map(user -> (Dealer) user)
                .findAny()
                .orElseThrow(() -> new IllegalStateException(NOT_CONTAIN_DEALER));
    }

    public List<String> getPlayerNames() {
        return users.stream()
                .filter(user -> user instanceof Player)
                .map(User::getName)
                .collect(Collectors.toUnmodifiableList());
    }

    public void drawDealer(final Deck deck) {
        getDealer().drawCard(deck);
    }

    public User getUser(final String name) {
        return users.stream()
                .filter(user -> user.getName().equals(name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(NOT_CONTAIN_USER_BY_NAME));
    }

    public Map<String, GameResult> getGameResult() {
        final Dealer dealer = getDealer();
        final Map<String, GameResult> gameResult = new HashMap<>();
        for (final Player player : getPlayers()) {
            gameResult.put(player.getName(), dealer.comparePlayer(player));
        }
        return gameResult;
    }

    private List<Player> getPlayers() {
        return users.stream()
                .filter(user -> user instanceof Player)
                .map(user -> (Player) user)
                .collect(Collectors.toUnmodifiableList());
    }
}
