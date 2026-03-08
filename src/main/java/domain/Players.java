package domain;

import common.ErrorMessage;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Players {
    private static final int MAX_PLAYER_NUMBER = 5;

    private final List<Player> players;

    private Players(List<Player> players) {
        this.players = players;
    }

    public static Players of(List<String> playerNames, Deck totalDeck) {
        return new Players(createPlayers(playerNames, totalDeck));
    }

    private static List<Player> createPlayers(List<String> playerNames, Deck totalDeck) {
        if (playerNames.size() > MAX_PLAYER_NUMBER) {
            throw new IllegalArgumentException(ErrorMessage.MAX_PLAYER_ERROR.getMessage());
        }

        List<Player> players = new ArrayList<>();
        playerNames.forEach(
                name -> players.add(
                        new Player(Deck.createParticipantDeck(totalDeck), name)
                )
        );

        return players;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Map<String, List<Card>> getDecksPerPlayer() {
        Map<String, List<Card>> decksPerUser = new LinkedHashMap<>();
        for (Player player : players) {
            decksPerUser.put(
                    player.getName(),
                    player.getDeck().getCards()
            );
        }
        return decksPerUser;
    }
}
