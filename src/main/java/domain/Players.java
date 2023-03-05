package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Players {

    private static final String PLAYER_DUPLICATE_NAME_ERROR_MESSAGE = "참여자 이름은 중복될 수 없습니다.";

    private final List<Player> players;

    public Players(List<Player> players) {
        this.players = players;
    }

    public static Players of(List<String> playerNames, List<CardDeck> cardDecks) {
        playerNames = playerNames.stream()
                .map(String::trim)
                .collect(Collectors.toList());
        validateDuplicateName(playerNames);
        return new Players(initializePlayers(playerNames, cardDecks));
    }

    private static void validateDuplicateName(List<String> playerNames) {
        long deduplicatedNameCount = playerNames.stream()
                .distinct()
                .count();

        if (deduplicatedNameCount != playerNames.size()) {
            throw new IllegalArgumentException(PLAYER_DUPLICATE_NAME_ERROR_MESSAGE);
        }
    }

    private static List<Player> initializePlayers(List<String> playerNames, List<CardDeck> cardDecks) {
        List<Player> players = new ArrayList<>();
        for (int index = 0; index < playerNames.size(); index++) {
            players.add(createPlayer(playerNames.get(index), cardDecks.get(index)));
        }
        return players;
    }

    private static Player createPlayer(String playerName, CardDeck cardDeck) {
        return new Player(new Name(playerName), cardDeck);
    }

    public List<Player> getPlayers() {
        return List.copyOf(players);
    }

}
