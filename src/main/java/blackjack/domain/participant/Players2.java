package blackjack.domain.participant;

import blackjack.domain.card.CardDeck;
import blackjack.domain.game.PlayersResult;
import blackjack.domain.game.Result2;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Players2 {

    private final List<Player2> players;

    public Players2(List<Player2> players) {
        this.players = players;
    }

    public void deal(CardDeck cardDeck) {
        players.forEach(player -> player.deal(cardDeck));
    }

    public void draw(Consumer<Player2> drawToPlayer) {
        players.forEach(drawToPlayer);
    }

    public PlayersResult judge(Dealer2 dealer) {
        Map<Player2, Result2> results = players.stream()
                .collect(Collectors.toMap(
                        player -> player,
                        player -> Result2.determineResult(player, dealer)
                ));

        return new PlayersResult(results);
    }

    public List<Player2> getPlayers() {
        return List.copyOf(players);
    }
}
