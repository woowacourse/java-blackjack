package blackjack.domain;

import blackjack.domain.card.CardGenerator;
import blackjack.domain.card.Deck;
import blackjack.domain.user.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Game {

    private static final int PLAYER_STAY_LIMIT = 21;
    private static final int DEALER_STAY_LIMIT = 16;

    private final User dealer;
    private final List<User> players;
    private final Deck deck;

    public Game(List<String> names) {
        deck = new Deck(CardGenerator.makeShuffledNewDeck());
        dealer = new Dealer(deck.pickInitialCards(), DEALER_STAY_LIMIT);
        players = createPlayer(names);
    }

    private List<User> createPlayer(List<String> names) {
        return names.stream()
            .map(name -> new Player(name, deck.pickInitialCards(), PLAYER_STAY_LIMIT))
            .collect(Collectors.toList());
    }

    public boolean hasHitPlayer() {
        return players.stream()
            .anyMatch(User::isHit);
    }

    public User bringHitPlayer() {
        return players.stream()
            .filter(User::isHit)
            .findFirst()
            .orElseThrow(IllegalArgumentException::new);
    }

    public boolean giveCardToDealer() {
        return dealer.draw(deck);
    }

    public void giveCardToPlayer(User player) {
        player.draw(deck);
    }

    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        users.add(dealer);
        users.addAll(players);
        return users;
    }

    public List<WinningResult> getWinningResults() {
        return players.stream()
            .map(player -> new WinningResult(player, MatchResult.calculateResult(player, dealer)))
            .collect(Collectors.toList());
    }

    public User getDealer() {
        return dealer;
    }

    public List<User> getPlayers() {
        return players;
    }
}
