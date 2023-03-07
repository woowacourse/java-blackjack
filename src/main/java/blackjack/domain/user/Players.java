package blackjack.domain.user;

import blackjack.domain.card.CardGroup;
import blackjack.domain.card.Deck;
import blackjack.domain.result.CardResult;
import blackjack.domain.result.WinningStatus;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Players {

    private static final String NUMBER_OF_PLAYER_OVER_LIMIT = "플레이어의 이름은 5개까지만 입력해야 합니다.";
    private static final String NOT_CONTAIN_USER_BY_NAME = "해당 이름의 유저를 찾을 수 없습니다.";
    private static final int NUMBER_OF_PLAYER_LIMIT = 5;

    private final List<Player> players;

    public Players(final List<String> playerNames, final Deck deck) {
        validatePlayerNames(playerNames);
        this.players = playerNames.stream()
                .map(name -> new Player(name, deck.drawFirstCardGroup()))
                .collect(Collectors.toUnmodifiableList());
    }

    private void validatePlayerNames(final List<String> playerNames) {
        if (playerNames.size() > NUMBER_OF_PLAYER_LIMIT) {
            throw new IllegalArgumentException(NUMBER_OF_PLAYER_OVER_LIMIT);
        }
    }

    public Map<String, CardGroup> getFirstOpenCardGroup() {
        final Map<String, CardGroup> firstOpenCardGroup = new LinkedHashMap<>();
        players.forEach(player ->
                firstOpenCardGroup.put(player.getName(), player.getFirstOpenCardGroup()));
        return Collections.unmodifiableMap(firstOpenCardGroup);
    }

    public Map<String, CardGroup> getStatus() {
        return players.stream()
                .collect(Collectors.toUnmodifiableMap(Player::getName, Player::getCardGroups));
    }

    public List<String> getPlayerNames() {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.toUnmodifiableList());
    }

    public Map<String, WinningStatus> getWinningResult(final Dealer dealer) {
        return players.stream()
                .collect(Collectors.toUnmodifiableMap(
                        Player::getName,
                        player -> player.calculatePlayerWinningStatus(dealer)));
    }

    public boolean isPlayerBust(final String name) {
        return players.stream()
                .filter(player -> player.isSameName(name))
                .map(Player::isBust)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(NOT_CONTAIN_USER_BY_NAME));
    }

    public void drawCard(final String userName, final Deck deck) {
        players.stream()
                .filter(player -> player.isSameName(userName))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(NOT_CONTAIN_USER_BY_NAME))
                .drawCard(deck);
    }

    public boolean isBlackJackScore(final String name) {
        return players.stream()
                .filter(player -> player.isSameName(name))
                .map(Player::isBlackJackScore)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(NOT_CONTAIN_USER_BY_NAME));
    }

    public Map<String, CardResult> getPlayerNameAndCardResults() {
        final Map<String, CardResult> playerNameAndCardResults = new LinkedHashMap<>();
        players.forEach(player -> playerNameAndCardResults.put(player.getName(),
                new CardResult(player.getCardGroups(), player.getScore())));
        return Collections.unmodifiableMap(playerNameAndCardResults);
    }
}
