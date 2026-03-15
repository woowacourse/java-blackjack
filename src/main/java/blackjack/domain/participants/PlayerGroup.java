package blackjack.domain.participants;

import blackjack.domain.card.ShuffledDeck;
import java.util.List;

public record PlayerGroup(List<Player> players) {

    public List<Player> players() {
        return List.copyOf(players);
    }

    public void deal(ShuffledDeck deck) {
        players.forEach(participant -> participant.hit(deck.draw()));
    }
}
