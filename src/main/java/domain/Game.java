package domain;

import static domain.Status.PLAYING;
import static domain.user.User.NUMBER_OF_FIRST_CARDS;

import domain.card.Deck;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.User;
import domain.user.Users;
import java.util.List;

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

        return status == PLAYING;
    }

    public void dealCardToDealer() {
        Dealer dealer = users.dealer();

        if (dealer.canHit()) {
            dealer.addCard(deck.drawCard());
        }
    }

    public void updateStatusToStay(boolean isYes, Player player) {
        player.updateStatusToStay(isYes);
    }

    public List<Player> getPlayers() {
        return users.players();
    }

    public Users getUsers() {
        return users;
    }
}
