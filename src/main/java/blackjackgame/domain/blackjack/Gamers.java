package blackjackgame.domain.blackjack;

import blackjackgame.domain.card.Deck;
import java.util.List;

public class Gamers {
    private final List<Gamer> players;

    public Gamers(List<Gamer> players) {
        this.players = players;
    }

    public void drawNTimes(Deck deck, int execution_count) {
        players.forEach(player -> player.draw(deck, new PlayerRandomCardDrawStrategy(player), execution_count));
    }

    public List<String> getPlayersNames() {
        return players.stream()
                .map(Gamer::getRawName)
                .toList();
    }

    public List<Gamer> getPlayers() {
        return players;
    }
}
