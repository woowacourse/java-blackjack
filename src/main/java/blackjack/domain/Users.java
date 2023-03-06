package blackjack.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Users {

    private static final String NOT_CONTAIN_DEALER = "Users에 Dealer 객체가 없습니다.";
    private static final String NOT_CONTAIN_USER_BY_NAME = "해당 이름의 유저를 찾을 수 없습니다.";
    private static final String PLAYER_NAMES_IS_EMPTY = "쉼표만 입력할 수 없습니다.";
    private static final String NUMBER_OF_PLAYER_OVER_LIMIT = "플레이어의 이름은 5개까지만 입력해야 합니다.";
    private static final int DEALER_DRAW_LIMIT = 16;
    private static final int NUMBER_OF_PLAYER_LIMIT = 5;

    private final List<User> users;

    public Users(final List<String> playerNames, final Deck deck) {
        validatePlayerNames(playerNames);
        final List<User> users = playerNames.stream()
                .map(name -> new Player(name, initCardGroup(deck)))
                .collect(Collectors.toList());
        users.add(new Dealer(initCardGroup(deck)));
        this.users = List.copyOf(users);
    }

    private void validatePlayerNames(final List<String> playerNames) {
        if (playerNames.isEmpty()) {
            throw new IllegalArgumentException(PLAYER_NAMES_IS_EMPTY);
        }
        if (playerNames.size() > NUMBER_OF_PLAYER_LIMIT) {
            throw new IllegalArgumentException(NUMBER_OF_PLAYER_OVER_LIMIT);
        }
    }

    private CardGroup initCardGroup(Deck deck) {
        return new CardGroup(deck.draw(), deck.draw());
    }

    public Map<String, List<Card>> getStatus() {
        return users.stream()
                .collect(Collectors.toUnmodifiableMap(User::getName, User::getStatus));
    }

    public Map<String, List<Card>> getFirstOpenCardGroups() {
        return users.stream()
                .collect(Collectors.toUnmodifiableMap(User::getName, User::getFirstOpenCardGroup));
    }

    public boolean isDealerOverDrawLimit() {
        return BlackJackRule.getScore(getDealer()) > DEALER_DRAW_LIMIT;
    }

    private User getDealer() {
        return users.stream()
                .filter(user -> user instanceof Dealer)
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

    public Map<String, WinningStatus> getWinningResult() {
        final Dealer dealer = (Dealer) getDealer();
        final Map<String, WinningStatus> winningResult = new HashMap<>();
        for (final Player player : getPlayers()) {
            winningResult.put(player.getName(), dealer.comparePlayer(player));
        }
        return winningResult;
    }

    private List<Player> getPlayers() {
        return users.stream()
                .filter(user -> user instanceof Player)
                .map(user -> (Player) user)
                .collect(Collectors.toUnmodifiableList());
    }
}
