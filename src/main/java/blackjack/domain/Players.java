package blackjack.domain;

import blackjack.domain.participant.Player;

import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Players {
    private final List<Player> players;

    private Players(List<Player> players) {
        validate(players);
        this.players = List.copyOf(players);
    }

    private void validate(List<Player> players) {
        Objects.requireNonNull(players, "플레이어 목록은 null일 수 없습니다.");
        if (players.isEmpty()) {
            throw new IllegalArgumentException("플레이어는 1명 이상이어야 합니다.");
        }
    }

    public static Players of(List<Player> players) {
        return new Players(players);
    }

    public void deal(Supplier<TrumpCard> cardSupplier) {
        forEach(player -> {
            player.hit(cardSupplier.get());
            player.hit(cardSupplier.get());
        });
    }

    public void betPlayers(BetDecision decision){
        players.forEach(player -> {
            String amount = decision.decideBet(player.getName());
            player.placeBet(amount);
        });
    }

    public void forEach(Consumer<Player> action){
        players.forEach(action);
    }

    public int count() {
        return players.size();
    }

    public List<Player> getPlayers() {
        return List.copyOf(players);
    }
}
