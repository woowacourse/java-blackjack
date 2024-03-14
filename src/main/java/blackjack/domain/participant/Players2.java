package blackjack.domain.participant;

import blackjack.domain.card.CardDeck;
import java.util.List;

public class Players2 {

    private final List<Player2> players;

    public Players2(List<Player2> players) {
        this.players = players;
    }

    public void deal(CardDeck cardDeck) {
        players.forEach(player -> player.deal(cardDeck));
    }
}
