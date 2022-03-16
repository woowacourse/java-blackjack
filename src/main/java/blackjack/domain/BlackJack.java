package blackjack.domain;

import static java.util.stream.Collectors.toList;

import blackjack.domain.card.Deck;
import blackjack.domain.strategy.DeckGenerateStrategy;
import blackjack.domain.user.BettingMoney;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.User;
import blackjack.domain.user.Users;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class BlackJack {

    private final Deck deck;
    private final Users users;

    private BlackJack(Deck deck, Users users) {
        this.deck = deck;
        this.users = users;
    }

    public static BlackJack from(Map<String, BettingMoney> playerInfo, DeckGenerateStrategy deckGenerateStrategy) {
        Users users = createUser(playerInfo);

        Deck deck = new Deck(deckGenerateStrategy);

        return new BlackJack(deck, users);
    }

    private static Users createUser(Map<String, BettingMoney> playerInfo) {
        List<User> players = playerInfo.entrySet()
                .stream()
                .map(entry -> Player.from(entry.getKey(), entry.getValue()))
                .collect(toList());

        Dealer dealer = new Dealer();

        return Users.of(players, dealer);
    }

    public void setInitCardsPerPlayer() {
        users.drawCards(deck);
        users.drawCards(deck);
    }

    public void drawAdditionalCard(Consumer<User> consumerPlayer, Consumer<User> consumerDealer) {
        users.drawAdditionalCard(consumerPlayer, consumerDealer);
    }

    public Users getUsers() {
        return this.users;
    }

    public Deck getDeck() {
        return this.deck;
    }
}
