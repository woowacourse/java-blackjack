package blackjack.domain;

import blackjack.domain.card.Deck;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.Users;

import java.util.List;

public class BlackjackGame {
    private final Deck deck;
    private final Users users;

    public BlackjackGame(List<String> names) {
        this.deck = new Deck();
        this.users = new Users(new Dealer(), names);
    }

    public void distributeToUsers() {
        this.users.getDealer().distribute(this.deck.popTwo());
        this.users.distributeToPlayer(this.deck);
    }

    public Deck getDeck() {
        return this.deck;
    }

    public Users getUsers() {
        return this.users;
    }

    public Dealer getDealer() {
        return this.users.getDealer();
    }

    public List<Player> getPlayers() {
        return this.users.getPlayers();
    }
}
