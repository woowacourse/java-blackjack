package domain;

import java.util.ArrayList;
import java.util.List;

public class Players {

    private final List<Player> users;
    private final Dealer dealer;

    public Players(List<Player> users, Dealer dealer) {
        this.users = users;
        this.dealer = dealer;
    }

    public void dealCardsFrom(Deck deck) {
        for (Player user : users) {
            user.addCard(deck.drawCard());
        }
        dealer.addCard(deck.drawCard());
    }

    public Result getUserResult(Player player) {
        return player.compare(dealer);
    }

    public List<Result> getDealerResults() {
        List<Result> results = new ArrayList<>();

        for (Player player : users) {
            results.add(dealer.compare(player));
        }
        return results;
    }

    public List<Player> getUsers() {
        return users;
    }

    public Dealer getDealer() {
        return dealer;
    }
}
