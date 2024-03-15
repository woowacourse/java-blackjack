package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Player;
import blackjack.dto.PlayerInfos;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Players {

    private static final int BLACKJACK_INIT_CARD_AMOUNT = 2;

    private final List<Player> players;

    private Players(final List<Player> players) {
        this.players = players;
    }

    public static Players from(final PlayerInfos playerInfos) {
        List<Player> players = playerInfos.infos().entrySet().stream()
                .map(entry -> new Player(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());

        return new Players(players);
    }

    public void initialDeal(final Supplier<Card> supplier) {
        IntStream.range(0, BLACKJACK_INIT_CARD_AMOUNT)
                .forEach(i -> players
                        .forEach(player -> player.draw(supplier.get())));
    }

    public List<String> getNames() {
        return players.stream()
                .map(Player::getName)
                .toList();
    }

    public List<Player> getPlayers() {
        return players;
    }
}
