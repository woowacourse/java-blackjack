package blackjack.manager;

import blackjack.domain.Dealer;
import blackjack.domain.Deck;
import blackjack.domain.Hand;
import blackjack.domain.Player;
import blackjack.domain.Players;
import java.util.List;

public class BlackJackInitManager {

    private final DeckGenerator deckGenerator;

    public BlackJackInitManager(DeckGenerator deckGenerator) {
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
