package blackjack.domain.participant;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class Players {

    public static final int MAX_PLAYER = 7;

    private final List<Player> players;

    public Players(final List<Player> players) {
        this.players = players;
        validatePlayerCount();
    }

    private void validatePlayerCount() {
        if (players.size() > MAX_PLAYER) {
            throw new IllegalArgumentException("최대 참여 플레이어는 " + MAX_PLAYER + "명입니다.");
        }
    }

    public void initHandByDealer(final Dealer dealer) {
        for (Player player : this.players) {
            player.receiveFirstHand(dealer.drawCards());
        }
    }

    public Stream<Player> stream() {
        return players.stream();
    }

    public <R> Stream<R> map(Function<? super Player, ? extends R> function) {
        return this.players.stream().map(function);
    }

    public List<Player> toList() {
        return Collections.unmodifiableList(this.players);
    }
}
