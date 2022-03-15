package blackjack.domain;

import blackjack.domain.card.Deck;
import blackjack.domain.strategy.DeckGenerateStrategy;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.User;
import blackjack.domain.user.Users;
import java.util.List;
import java.util.function.Consumer;

public class BlackJack {

    private final Deck deck;
    private final Users users;

    private BlackJack(Deck deck, Users users) {
        this.deck = deck;
        this.users = users;
    }

    public static BlackJack from(List<String> playerNames, DeckGenerateStrategy deckGenerateStrategy) {
        Users users = createUser(playerNames);

        Deck deck = new Deck(deckGenerateStrategy);

        return new BlackJack(deck, users);
    }

    private static Users createUser(List<String> playerNames) {
        Dealer dealer = new Dealer();

        return Users.of(playerNames, dealer);
    }

    public void setInitCardsPerPlayer() {
        users.setInitCardsPerPlayer(deck);
    }

    public void drawAdditionalCard(Consumer<User> consumerPlayer, Consumer<User> consumerDealer) {
        users.drawAdditionalCard(consumerPlayer, consumerDealer);
    }

    public void calculateScore() {
        users.calculateAllUser();
    }

    public Users getUsers() {
        return this.users;
    }

    public Deck getDeck() {
        return this.deck;
    }
}
