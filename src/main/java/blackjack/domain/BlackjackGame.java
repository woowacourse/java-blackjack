package blackjack.domain;

import blackjack.domain.card.Deck;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Players;
import blackjack.domain.user.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BlackjackGame {
    private final Deck deck = new Deck();
    private final Dealer dealer = new Dealer();
    private final List<User> users = new ArrayList<>();

    private Players players;

    public void setUpPlayers(List<String> names) {
        this.players = new Players(names);
    }

    public void setUpUsers() {
        this.users.add(this.dealer);
        this.users.addAll(this.players.getPlayers());
    }

    public void distributeToUsers() {
        this.dealer.distribute(this.deck.popTwo());
        this.players.distributeToPlayer(this.deck);
    }

    public Deck getDeck() {
        return this.deck;
    }

    public Dealer getDealer() {
        return this.dealer;
    }

    public List<User> getUsers() {
        return Collections.unmodifiableList(this.users);
    }

    public Players getPlayers() {
        return this.players;
    }
}
