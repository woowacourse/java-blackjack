package blackjack.domain;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackJackGame {

    private final Deck deck;
    private final List<User> users;

    public BlackJackGame(final List<String> playerNames, final DeckGenerator deckGenerator) {
        this.deck = new Deck(deckGenerator);
        List<User> users = playerNames.stream()
                .map(name -> new Player(name, initCardGroup()))
                .collect(Collectors.toList());
        users.add(new Dealer(initCardGroup()));
        this.users = List.copyOf(users);
    }

    private CardGroup initCardGroup() {
        return new CardGroup(deck.draw(), deck.draw());
    }

    public Map<String, List<Card>> getStatus() {
        return users.stream()
                .collect(Collectors.toUnmodifiableMap(User::getName, User::getStatus));
    }

    public Map<String, List<Card>> getInitialStatus() {
        return users.stream()
                .collect(Collectors.toUnmodifiableMap(User::getName, User::getInitialStatus));
    }
}
