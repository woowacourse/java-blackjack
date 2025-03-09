package blackjack.manager;

import blackjack.domain.CardHolder;
import blackjack.domain.Dealer;
import blackjack.domain.Deck;
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

    public Players generatePlayers(List<String> names, Supplier<CardHolder> cardHolderSupplier) {
        List<Player> players = names.stream()
                .map(name -> new Player(name, cardHolderSupplier.get()))
                .toList();

        return Players.from(players);
    }

    public Dealer generateDealer(Supplier<CardHolder> cardHolderSupplier) {
        return new Dealer(cardHolderSupplier.get());
    }
}
