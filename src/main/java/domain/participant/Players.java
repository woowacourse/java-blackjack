package domain.participant;

import domain.card.Deck;
import domain.card.Hand;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Consumer;
import java.util.function.Function;

public class Players {
    private final List<Player> players;

    public static Players from(final Map<Name, Money> playerBets) {
        List<Player> players = new ArrayList<>();
        for (Entry<Name, Money> entry : playerBets.entrySet()) {
            players.add(new Player(new Hand(List.of()), entry.getKey(), entry.getValue()));
        }
        return new Players(players);
    }

    private Players(final List<Player> players) {
        this.players = players;
    }

    public void hitCards(final Deck deck) {
        players.forEach(player -> player.hitCards(deck));
    }

    public void draw(final Function<Player, Boolean> answer, final Consumer<Player> playerDeck, final Deck deck) {
        for (Player player : players) {
            player.draw(answer, playerDeck, deck);
        }
    }

    public Map<Player, Integer> calculatePlayerProfit(final int dealerSum) {
        LinkedHashMap<Player, Integer> bettingResults = new LinkedHashMap<>();
        for (Player player : players) {
            bettingResults.put(player, player.calculateProfit(dealerSum));
        }
        return bettingResults;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
