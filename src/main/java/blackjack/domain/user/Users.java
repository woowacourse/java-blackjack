package blackjack.domain.user;

import static java.util.stream.Collectors.toList;

import blackjack.domain.Rule;
import blackjack.domain.card.Deck;
import java.util.Collections;
import java.util.List;

public class Users {

    private final List<Player> players;
    private final Dealer dealer;

    private Users(List<Player> players) {
        this.players = players;
        dealer = new Dealer();
    }

    public static Users from(List<String> inputNames) {
        List<Player> players = inputNames.stream()
                .map(Player::new)
                .collect(toList());

        return new Users(players);
    }

    public void setInitCardsPerPlayer(Deck deck) {
        for (Player player : players) {
            player.drawInitCards(deck);
        }
        dealer.drawInitCards(deck);
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void calculateAllUser(Rule rule) {
        for (Player player : players) {
            player.calculate(rule);
        }
        dealer.calculate(rule);
    }
}
