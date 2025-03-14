package blackjack.domain.participants;

import blackjack.domain.card.Deck;
import blackjack.domain.state.State;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Players {

    private static final int PLAYER_MIN_SIZE = 2;
    private static final int PLAYER_MAX_SIZE = 10;

    private final List<Player> players;

    public Players(Player... players) {
        this(new ArrayList<>(List.of(players)));
    }

    public Players(List<Player> players) {
        validatePlayersSize(players);
        this.players = players;
    }

    private void validatePlayersSize(List<Player> players) {
        if (players.size() > PLAYER_MAX_SIZE) {
            throw new IllegalArgumentException("플레이어 수는 최대 " + PLAYER_MAX_SIZE + "명입니다.");
        }
        if (players.size() < PLAYER_MIN_SIZE) {
            throw new IllegalArgumentException("플레이어 수는 최소 " + PLAYER_MIN_SIZE + "명입니다.");
        }
    }

    public void drawCard(Deck deck, Player player) {
        if (!players.contains(player)) {
            throw new IllegalArgumentException("해당 플레이어는 존재하지 않습니다.");
        }
        player.drawCard(deck);
    }

    public void sendAll(Consumer<Player> consumer) {
        for (Player player : players) {
            consumer.accept(player);
        }
    }

    public int calculateTotalProfit(State competitiveState) {
        return players.stream()
                .mapToInt(player -> player.calculateProfit(competitiveState))
                .sum();
    }

    public Map<Player, Integer> calculateAllProfit(Dealer dealer) {
        return players.stream()
                .collect(Collectors.toMap(
                        player -> player,
                        player -> player.calculateProfit(dealer.getState())
                ));
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        Players players1 = (Players) object;
        return Objects.equals(getPlayers(), players1.getPlayers());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getPlayers());
    }
}
