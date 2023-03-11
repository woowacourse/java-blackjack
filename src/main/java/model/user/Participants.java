package model.user;

import static java.util.stream.Collectors.toList;

import java.util.List;
import model.card.Deck;

public class Participants {

    private final List<Player> players;
    private final Dealer dealer;

    private Participants(final List<Player> playersName, final Dealer dealer) {
        this.players = playersName;
        this.dealer = dealer;
    }

    public static Participants of(final List<String> playersName, final Dealer dealer) {
        return new Participants(createPlayers(playersName), dealer);
    }

    private static List<Player> createPlayers(List<String> playersName) {
        return playersName.stream()
                .map(Player::new)
                .collect(toList());
    }

    public void receiveInitialCards(final Deck deck) {
        players.forEach(player -> player.receiveInitialCards(deck));
        dealer.receiveInitialCards(deck);
    }

    public List<Player> getPlayers() {
        return this.players;
    }

    public Dealer getDealer() {
        return this.dealer;
    }
}
