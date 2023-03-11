package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.participant.exception.PlayerNotFoundException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Players {

    private static final int MAX_PLAYER_COUNT = 5;
    private static final int MIN_PLAYER_COUNT = 1;
    private static final String OVER_RANGE_MESSAGE =
            "사용자 수는 " + MIN_PLAYER_COUNT + " 이상 " + MAX_PLAYER_COUNT + " 이하여야 합니다. 현재 : %s 명입니다";
    private final List<Player> players;

    private Players(final List<Player> players) {
        this.players = players;
    }

    public static Players from(final List<String> playerNames) {
        validatePlayerNames(playerNames);
        final List<Player> players = createPlayers(playerNames);
        return new Players(players);
    }

    private static List<Player> createPlayers(final List<String> playerNames) {
        return playerNames.stream()
                .map(Player::new)
                .collect(Collectors.toList());
    }

    private static void validatePlayerNames(final List<String> playerNames) {
        validateNull(playerNames);
        validatePlayerCount(playerNames);
        validateDuplicate(playerNames);
    }

    private static void validateNull(final List<String> playerNames) {
        if (playerNames == null) {
            throw new IllegalArgumentException("사용자 이름이 입력되지 않았습니다");
        }
    }

    private static void validatePlayerCount(final List<String> playerNames) {
        if (MIN_PLAYER_COUNT > playerNames.size() || playerNames.size() > MAX_PLAYER_COUNT) {
            throw new IllegalArgumentException(String.format(OVER_RANGE_MESSAGE, playerNames.size()));
        }
    }

    private static void validateDuplicate(final List<String> playerNames) {
        if (playerNames.stream().distinct().count() != playerNames.size()) {
            throw new IllegalArgumentException("사용자의 이름이 중복됩니다.");
        }
    }

    void distributeInitialCards(final Deck deck) {
        for (final Player player : players) {
            final Card firstCard = deck.popCard();
            final Card secondCard = deck.popCard();
            player.drawInitialCard(firstCard, secondCard);
        }
    }

    public List<String> getPlayerNames() {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.toList());
    }

    boolean isDrawable(final String playerName) {
        return players.stream()
                .filter(player -> player.hasName(playerName))
                .findFirst()
                .map(Player::isDrawable)
                .orElseThrow(PlayerNotFoundException::new);
    }

    void draw(final String playerName, final Card card) {
        final Player targetPlayer = players.stream()
                .filter(player -> player.hasName(playerName))
                .findFirst()
                .orElseThrow(PlayerNotFoundException::new);
        targetPlayer.drawCard(card);
    }

    public Player findPlayerByName(final String name) {
        return players.stream()
                .filter(player -> player.hasName(name))
                .findFirst()
                .orElseThrow(PlayerNotFoundException::new);
    }

    public List<String> getNames() {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.toList());
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Map<String, Integer> calculatePlayersScore() {
        final Map<String, Integer> playerScore = new LinkedHashMap<>();
        for (final Player player : players) {
            playerScore.put(player.getName(), player.currentScore());
        }
        return playerScore;
    }
}
