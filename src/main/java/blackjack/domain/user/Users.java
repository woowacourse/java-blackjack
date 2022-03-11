package blackjack.domain.user;

import blackjack.domain.Rule;
import blackjack.domain.card.Deck;
import java.util.List;

public class Users {

    private final Players players;
    private final Dealer dealer;

    private Users(Players players, Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    public static Users from(List<String> playerNames, Dealer dealer) {
        Players players = Players.from(playerNames);

        return new Users(players, dealer);
    }

    public void setInitCardsPerPlayer(Deck deck) {
        players.drawInitCards(deck);
        dealer.drawInitCards(deck);
    }

    public List<Player> getPlayers() {
        return players.getPlayers();
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void calculateAllUser(Rule rule) {
        players.calculate(rule);
        dealer.calculate(rule);
    }
}
