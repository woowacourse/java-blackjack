package domain.participant;

import domain.MatchResult;
import domain.card.Deck;
import domain.card.Hand;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class Players {
    private final List<Player> players;

    public static Players from(final Names names) {
        List<Player> players = names.getNames().stream()
                .map(name -> new Player(new Hand(List.of()), name))
                .toList();
        return new Players(players);
    }

    private Players(final List<Player> players) {
        this.players = players;
    }

    public void hitCards(final Deck cardDeck) {
        players.forEach(player -> player.hitCards(cardDeck));
    }

    public void draw(final Function<Player, Boolean> answer, final Consumer<Player> playerDeck, Deck cardDeck) {
        for (Player player : players) {
            player.draw(answer, playerDeck, cardDeck);
        }
    }

    public LinkedHashMap<Player, MatchResult> calculateWinner(final int dealerSum) {
        LinkedHashMap<Player, MatchResult> res = new LinkedHashMap<>();
        for (Player player : players) {
            res.put(player, player.calculateWinner(dealerSum));
        }
        return res;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
