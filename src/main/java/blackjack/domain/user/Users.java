package blackjack.domain.user;

import blackjack.domain.card.CardGroup;
import blackjack.domain.card.Deck;
import blackjack.domain.result.WinningStatus;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Users {

    private static final String NOT_CONTAIN_USER_BY_NAME = "해당 이름의 유저를 찾을 수 없습니다.";
    private static final String PLAYER_NAMES_IS_EMPTY = "쉼표만 입력할 수 없습니다.";
    private static final String NUMBER_OF_PLAYER_OVER_LIMIT = "플레이어의 이름은 5개까지만 입력해야 합니다.";
    private static final int DEALER_DRAW_LIMIT = 16;
    private static final int NUMBER_OF_PLAYER_LIMIT = 5;

    private Dealer dealer;
    private final List<Player> players;

    public Users(final List<String> playerNames, final Deck deck) {
        validatePlayerNames(playerNames);
        dealer = new Dealer(initCardGroup(deck));
        this.players = playerNames.stream()
                .map(name -> new Player(name, initCardGroup(deck)))
                .collect(Collectors.toUnmodifiableList());
    }

    private void validatePlayerNames(final List<String> playerNames) {
        if (playerNames.isEmpty()) {
            throw new IllegalArgumentException(PLAYER_NAMES_IS_EMPTY);
        }
        if (playerNames.size() > NUMBER_OF_PLAYER_LIMIT) {
            throw new IllegalArgumentException(NUMBER_OF_PLAYER_OVER_LIMIT);
        }
    }

    private CardGroup initCardGroup(final Deck deck) {
        return new CardGroup(deck.draw(), deck.draw());
    }

    public Map<String, CardGroup> getFirstOpenCardGroups() {
        Map<String, CardGroup> firstOpenCardGroups = players.stream()
                .collect(Collectors.toMap(User::getName, User::getFirstOpenCardGroup));
        firstOpenCardGroups.put(dealer.getName(), dealer.getFirstOpenCardGroup());
        return firstOpenCardGroups;
    }

    public Map<String, CardGroup> getStatus() {
        Map<String, CardGroup> status = players.stream()
                .collect(Collectors.toMap(Player::getName, Player::getCardGroups));
        status.put(dealer.getName(), dealer.getCardGroups());
        return status;
    }

    public boolean isDealerUnderDrawLimit() {
        return dealer.isUnderDrawLimit();
    }

    private User getDealer() {
        return dealer;
    }

    public List<String> getPlayerNames() {
        return players.stream()
                .map(User::getName)
                .collect(Collectors.toUnmodifiableList());
    }

    public void drawDealer(final Deck deck) {
        getDealer().drawCard(deck);
    }

    public Player getUser(final String name) {
        return players.stream()
                .filter(player -> player.getName().equals(name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(NOT_CONTAIN_USER_BY_NAME));
    }

    public Map<String, WinningStatus> getWinningResult() {
        final Dealer dealer = (Dealer) getDealer();
        final Map<String, WinningStatus> winningResult = new HashMap<>();
        for (final Player player : players) {
            winningResult.put(player.getName(), dealer.comparePlayer(player));
        }
        return winningResult;
    }
}
