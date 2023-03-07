package domain;

import java.util.List;

public class Game {

    private final Deck deck;
    private final Users users;

    public Game(List<Player> players, Deck deck, Dealer dealer) {
        this.users = new Users(players, dealer);
        this.deck = deck;
    }

    public void dealTwoCards() {
        for (int i = 0; i < 2; i++) {
            users.dealCardsFrom(deck);
        }
    }

    public Result getResultBy(User user) {
        return users.getUserResult(user);
    }

    public void dealCard(User user) {
        user.addCard(deck.drawCard());
    }

    public boolean canHitByPlayerScore(User user) {
        return user.canHit();
    }

    public boolean canHitByDealerScore() {
        return canHitByPlayerScore(users.dealer());
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
