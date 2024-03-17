package blackjack.domain.gamer;

import blackjack.domain.card.Card;

import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

public class Players {
    private final List<Player> players;

    public Players(List<Player> players) {
        this.players = players;
    }

    public void draw(Supplier<List<Card>> pickCard) {
        for (Player player : players) {
            player.draw(pickCard.get());
        }
    }


    public List<String> getNames() {
        return players.stream().map(Player::name).toList();
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public int getPlayersCount() {
        return players.size();
    }

    public Long allProfit() {
        return players.stream()
                .mapToLong(Player::profit)
                .sum();
    }
}
