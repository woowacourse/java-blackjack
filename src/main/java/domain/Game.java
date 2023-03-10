package domain;

import java.util.List;

import static domain.Status.BUST;
import static domain.Status.STAY;
import static domain.User.NUMBER_OF_FIRST_CARDS;

public class Game {

    private final Deck deck;
    private final Users users;

    public Game(List<Player> players, Deck deck, Dealer dealer) {
        this.users = new Users(players, dealer);
        this.deck = deck;
    }

    public void dealCardsInFirstTurn() {
        for (int i = 0; i < NUMBER_OF_FIRST_CARDS; i++) {
            users.dealCardsFrom(deck);
        }
    }

    public Result getResultBy(User user) {
        return users.getUserResult(user);
    }

    public void dealCard(User user) {
        user.addCard(deck.drawCard());
    }


    public boolean canHitByDealerScore() {
        return canHitByPlayerScore(users.dealer());
    }

    public boolean canHitByPlayerScore(User user) {
        Status status = user.status();

        return status != STAY && status != BUST;
    }

    public void dealCardToDealer() {
        Dealer dealer = users.dealer();

        if (dealer.canHit()) {
            dealer.addCard(deck.drawCard());
        }
    }

    public List<Player> getPlayers() {
        return users.players();
    }

    public Users getUsers() {
        return users;
    }

}
