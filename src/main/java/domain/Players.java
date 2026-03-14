package domain;

import domain.participant.Player;
import util.InputNameParser;
import util.InputNameValidator;

import java.util.Iterator;
import java.util.List;

public class Players implements Iterable<Player>{

    private final List<Player> players;

    private Players(List<Player> players) {
        this.players = players;
    }

    public static Players fromString(String rawPlayerNames) {
        List<String> playerNames = InputNameParser.parsePlayerNames(rawPlayerNames);
        InputNameValidator.validateInputNames(playerNames);

        List<Player> players = playerNames.stream().map(Player::new).toList();
        return new Players(players);
    }

    public List<String> getPlayerNames() {
        return players.stream()
                .map(Player::getName)
                .toList();
    }

    @Override
    public Iterator<Player> iterator() {
        return players.iterator();
    }
}
