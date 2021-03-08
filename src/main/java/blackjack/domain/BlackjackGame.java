package blackjack.domain;

import blackjack.domain.card.Deck;
import blackjack.domain.result.ResultBoard;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Players;
import blackjack.domain.user.User;

import java.util.ArrayList;
import java.util.List;

public class BlackjackGame {
    private final Dealer dealer;
    private final Players players;
    private final Deck deck;

    public BlackjackGame(Dealer dealer, Players players) {
        this.dealer = dealer;
        this.players = players;
        this.deck = new Deck();
    }

    public static BlackjackGame generateByUser(List<String> names) {
        Dealer dealer = new Dealer();
        Players players = Players.of(names);
        return new BlackjackGame(dealer, players);
    }

    public void handOutInitialCards() {
        dealer.receiveCards(deck.popTwo());
        players.getPlayers()
                .forEach(player -> player.receiveCards(deck.popTwo()));
    }

    public boolean isNotGameOver(User user) {
        return user.isAbleToHit();
    }

    public void hit(User user) {
        user.receiveCards(deck.popOne());
    }

    public ResultBoard generateResultBoard() {
        return ResultBoard.of(players, dealer);
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Players getPlayers() {
        return players;
    }

    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        users.add(dealer);
        users.addAll(players.getPlayers());
        return users;
    }
}
