package domain.user;

import domain.Referee;
import domain.card.Deck;
import domain.Result;

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
        return Referee.getResult(user, dealer);
    }

    public List<Result> getDealerResults() {
        List<Result> results = new ArrayList<>();

        for (Player player : players) {
            results.add(Referee.getResult(dealer, player));
        }

        return results;
    }

    public List<Player> players() {
        return players;
    }

    public Dealer dealer() {
        return dealer;
    }
}
