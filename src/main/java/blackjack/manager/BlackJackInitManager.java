package blackjack.manager;

import blackjack.domain.Dealer;
import blackjack.domain.Deck;
import blackjack.domain.Hand;
import blackjack.domain.Player;
import blackjack.domain.Players;
import java.util.List;

public class BlackJackInitManager {

    private final CardsGenerator cardsGenerator;

    public BlackJackInitManager(CardsGenerator cardsGenerator) {
        this.cardsGenerator = cardsGenerator;
    }

    public Deck generateDeck() {
        return new Deck(cardsGenerator.generate());
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
