package blackjack.manager;

import blackjack.domain.Dealer;
import blackjack.domain.Deck;
import blackjack.domain.Hand;
import blackjack.domain.Player;
import blackjack.domain.Players;
import java.util.List;
import java.util.function.Supplier;

public class BlackJackInitManager {

    private final CardsGenerator cardsGenerator;

    public BlackJackInitManager(CardsGenerator cardsGenerator) {
        this.cardsGenerator = cardsGenerator;
    }

    public Deck generateDeck() {
        return new Deck(cardsGenerator.generate());
    }

    public Players savePlayers(List<String> names, Supplier<Hand> cardHolderSupplier) {
        List<Player> players = names.stream()
                .map(name -> new Player(name, cardHolderSupplier.get()))
                .toList();

        return new Players(players);
    }

    public Dealer saveDealer(Supplier<Hand> cardHolderSupplier) {
        return new Dealer(cardHolderSupplier.get());
    }
}
