package domain;

import java.util.ArrayList;
import java.util.List;

public class Users {

    private final List<Player> players;
    private final Dealer dealer;

    public Users(List<Player> players, Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    public void dealCardsFrom(Deck deck) {
        for (Player player : players) {
            player.addCard(deck.drawCard());
        }
        dealer.addCard(deck.drawCard());
    }

    public Result getUserResult(User user) {
        return user.compare(dealer);
    }

    public List<Result> getDealerResults() {
        List<Result> results = new ArrayList<>();

        for (Player player : players) {
            results.add(dealer.compare(player));
        }
        return results;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Dealer getDealer() {
        return dealer;
    }
}
