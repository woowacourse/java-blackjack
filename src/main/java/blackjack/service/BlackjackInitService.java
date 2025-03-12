package blackjack.service;

import blackjack.domain.Dealer;
import blackjack.domain.Deck;
import blackjack.domain.Hand;
import blackjack.domain.Player;
import blackjack.domain.Players;
import blackjack.factory.DeckGenerator;
import java.util.List;

public class BlackjackInitService {

    private final DeckGenerator deckGenerator;

    public BlackjackInitService(DeckGenerator deckGenerator) {
        this.deckGenerator = deckGenerator;
    }

    public Deck generateDeck() {
        return new Deck(deckGenerator.generate());
    }

    public Players generatePlayers(List<String> names) {
        List<Player> players = names.stream()
                .map(name -> new Player(name, new Hand()))
                .toList();

        return Players.from(players);
    }

    public Dealer generateDealer() {
        return new Dealer(new Hand());
    }
}
