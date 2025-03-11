package domain.player;

import domain.card.Deck;
import java.util.ArrayList;
import java.util.List;

public class Players {

    private final Dealer dealer;
    private final Users users;


    public Players(Dealer dealer, Users users) {
        this.dealer = dealer;
        this.users = users;
    }

    public static Players of(Dealer dealer, Users users) {
        return new Players(dealer, users);
    }

    public void distributeInitialCards(Deck deck) {
        dealer.receiveInitialCards(deck);
        for (User user : users.getUsers()) {
            user.receiveInitialCards(deck);
        }
    }

    public void openInitialCards() {
        dealer.openInitialCards();
        for (User user : users.getUsers()) {
            user.openInitialCards();
        }
    }

    public int size() {
        return 1 + users.size();
    }

    public List<Player> getPlayers() {
        List<Player> players = new ArrayList<>(List.of(dealer));
        players.addAll(users.getUsers());
        return players;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<User> getUsers() {
        return users.getUsers();
    }
}
