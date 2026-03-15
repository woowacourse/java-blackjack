package blackjack.domain.participants;

import blackjack.domain.card.Deck;
import java.util.List;

public record PlayerGroup(List<Player> players) {

    public List<Player> players() {
        return List.copyOf(players);
    }

    public void deal(Deck deck) {
        players.forEach(participant -> participant.hit(deck.draw()));
    }
}
