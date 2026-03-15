package domain;

import domain.card.Deck;
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

    public void drawInitialCards(Deck deck) {
        for (Player player : players) {
            player.drawInitialCards(deck.drawInitialCards());
        }
    }

    public void endGameImmediately() {
        for (Player player : players) {
            forceStay(player);
        }
    }

    private void forceStay(Player player) {
        if (player.isRunning()) {
            player.stay();
        }
    }
    @Override
    public Iterator<Player> iterator() {
        return players.iterator();
    }
}
