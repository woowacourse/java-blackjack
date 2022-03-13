package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;

import java.util.ArrayList;
import java.util.List;

public class Gamers {
    private static final int INIT_DISTRIBUTION_COUNT = 2;

    private final Dealer dealer;
    private final List<Player> players = new ArrayList<>();
    public Gamers(List<String> names) {
        dealer = new Dealer();
        createPlayers(names);
    }

    private void createPlayers(List<String> names) {
        for (String name : names) {
            players.add(new Player(name));
        }
    }

    public void distributeFirstCards(Deck deck) {
        for (int i = 0; i < INIT_DISTRIBUTION_COUNT; i++) {
            distributeCard(dealer, deck);
            players.forEach(player -> distributeCard(player, deck));
        }
    }
    private void distributeCard(Gamer gamer, Deck deck) {
        gamer.addCard(deck.draw());
    }

    public Dealer findDealer() {
        return dealer;
    }

    public List<Player> findPlayers() {
        return players;
    }
}
