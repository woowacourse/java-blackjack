package domain.gamer;

import domain.gamer.exception.ErrorMessage;
import domain.gamer.exception.PlayerException;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Players {
    private final List<Player> players;

    private Players(List<Player> players) {
        this.players = players;
    }

    public static Players from(List<Player> players) {
        return new Players(players);
    }

    public static Players enterByPlayerNames(List<PlayerName> playerNames) {
        validateNoDuplicateNames(playerNames);
        List<Player> players = playerNames.stream()
                .map(Player::from)
                .toList();
        return Players.from(players);
    }

    public void dealCardBundle(Dealer dealer) {
        players.forEach(dealer::dealCardToPlayer);
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    private static void validateNoDuplicateNames(List<PlayerName> playerNames) {
        Set<PlayerName> uniqueNames = new HashSet<>(playerNames);
        if (uniqueNames.size() != playerNames.size()) {
            throw new PlayerException(ErrorMessage.DUPLICATED_PLAYER_NAME_ERROR);
        }
    }
}
