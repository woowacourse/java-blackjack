package blackjack.domain.player;

import blackjack.domain.card.Deck;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static blackjack.controller.BlackJackController.CARD_COUNT;

public class Players {
    private static final String PLAYER_COUNT_ERROR_MESSAGE = "플레이어 수는 1명 이상 7명 이하여야 합니다.";
    private static final String PLAYER_NAME_DUPLICATE_ERROR_MESSAGE = "플레이어 이름은 중복될 수 없습니다.";
    private static final int MIN_PLAYER_COUNT = 1;
    private static final int MAX_PLAYER_COUNT = 7;

    private final List<Player> players;

    private Players(List<Player> players) {
        this.players = players;
    }

    public static Players of(List<String> playerNames, List<String> bettingMoneys, Deck deck) {
        validatePlayerNames(playerNames);
        return new Players(IntStream.range(0, playerNames.size())
                .mapToObj(index -> new Player(playerNames.get(index),
                        bettingMoneys.get(index), deck.getCards(CARD_COUNT)))
                .collect(Collectors.toList()));
    }

    private static void validatePlayerNames(List<String> playerNames) {
        validatePlayerCount(playerNames);
        validateDuplicate(playerNames);
    }

    private static void validatePlayerCount(List<String> playerNames) {
        if (playerNames.size() < MIN_PLAYER_COUNT || playerNames.size() > MAX_PLAYER_COUNT) {
            throw new IllegalArgumentException(PLAYER_COUNT_ERROR_MESSAGE);
        }
    }

    private static void validateDuplicate(List<String> playerNames) {
        if (playerNames.size() != playerNames.stream().distinct().count()) {
            throw new IllegalArgumentException(PLAYER_NAME_DUPLICATE_ERROR_MESSAGE);
        }
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
