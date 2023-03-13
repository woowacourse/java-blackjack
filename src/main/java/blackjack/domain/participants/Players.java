package blackjack.domain.participants;

import blackjack.domain.card.Card;
import blackjack.domain.game.Deck;
import blackjack.domain.game.GameReferee;
import blackjack.domain.game.ResultType;
import blackjack.domain.game.Score;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Players {

    private static final int MAX_PLAYER_NUMBER = 6;
    private static final int MIN_PLAYER_NUMBER = 1;
    private static final String OVER_RANGE_MESSAGE =
            "사용자 수는 " + MIN_PLAYER_NUMBER + " 이상 " + MAX_PLAYER_NUMBER + " 이하여야 합니다. 현재 : %s 명입니다";
    private static final String ERROR_NOT_EXIST_PLAYER = "존재하지 않는 플레이어입니다";

    private final List<Player> players;

    private Players(final List<Player> players) {
        this.players = players;
    }

    public static Players from(final List<String> playerNames) {
        validatePlayerNames(playerNames);
        final List<Player> players = createPlayers(playerNames);
        return new Players(players);
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
        if (MAX_PLAYER_NUMBER < playerNames.size() || playerNames.size() < MIN_PLAYER_NUMBER) {
            throw new IllegalArgumentException(String.format(OVER_RANGE_MESSAGE, playerNames.size()));
        }
    }

    private static void validateDuplicate(final List<String> playerNames) {
        if (Set.copyOf(playerNames)
                .size() != playerNames.size()) {
            throw new IllegalArgumentException("사용자의 이름이 중복됩니다.");
        }
    }

    private static List<Player> createPlayers(final List<String> playerNames) {
        return playerNames.stream()
                .map(Player::new)
                .collect(Collectors.toList());
    }

    public void distributeInitialCards(final Deck deck) {
        for (final Player player : players) {
            player.drawCard(deck.popCard());
            player.drawCard(deck.popCard());
        }
    }

    public List<String> findPlayerNames() {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.toList());
    }

    public boolean isPlayerDrawable(final String playerName) {
        return players.stream()
                .filter(player -> player.hasName(playerName))
                .findFirst()
                .map(Player::isDrawable)
                .orElseThrow(() -> new IllegalArgumentException(ERROR_NOT_EXIST_PLAYER));
    }

    public void drawCardOfPlayerByName(final String playerName, final Card card) {
        final Player targetPlayer = players.stream()
                .filter(player -> player.hasName(playerName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ERROR_NOT_EXIST_PLAYER));

        targetPlayer.drawCard(card);
    }

    public List<Card> findCardsOfPlayerByName(final String playerName) {
        return players.stream()
                .filter(player -> player.hasName(playerName))
                .findAny()
                .map(Player::getCards)
                .orElseThrow(() -> new IllegalArgumentException(ERROR_NOT_EXIST_PLAYER));
    }

    public Map<String, ResultType> calculateResult(final GameReferee gameReferee) {
        return players.stream()
                .collect(Collectors.toMap(Player::getName,
                        gameReferee::findResultOfPlayer,
                        (x, y) -> y,
                        LinkedHashMap::new));
    }

    public Map<String, List<Card>> findPlayerNameToCards() {
        return players.stream()
                .collect(Collectors.toMap(Player::getName,
                        Participant::getCards,
                        (x, y) -> y,
                        LinkedHashMap::new));
    }

    public Map<Map<String, List<Card>>, Score> findPlayerStatusByName() {
        return players.stream()
                .collect(Collectors.toMap(player -> Map.of(player.getName(), player.getCards()),
                        Player::currentScore,
                        (x, y) -> y,
                        LinkedHashMap::new));
    }

}
