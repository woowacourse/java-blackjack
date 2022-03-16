package blackjack.domain.paticipant;

import blackjack.domain.card.CardDeck;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Players {

    private final List<Player> players;

    public Players(final List<Player> players) {
        Objects.requireNonNull(players, "players는 null이 들어올 수 없습니다.");
        this.players = new ArrayList<>(players);
    }

    public Players(final List<String> names, final Function<String, Integer> betMoney, final CardDeck cardDeck) {
        this(names.stream()
                .map(name -> Player.createPlayer(name, betMoney.apply(name), cardDeck))
                .collect(Collectors.toList()));
    }

    public int dealerProfit(final Dealer dealer) {
        return (int) - players.stream()
                .map(player -> player.profit(dealer))
                .count();
    }
}
