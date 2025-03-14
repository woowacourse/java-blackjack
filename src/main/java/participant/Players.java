package participant;

import deck.Deck;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import state.started.Started;

public class Players {

    private final List<Player> players;

    private Players(final List<String> names, final Deck deck) {
        this.players = names.stream()
                .map(nickname -> new Player(nickname, Started.start(deck.draw(), deck.draw())))
                .collect(Collectors.toList());
    }

    public static Players prepareGame(List<String> names, Deck deck) {
        return new Players(names, deck);
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
