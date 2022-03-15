package blackjack.domain.user;

import static java.util.stream.Collectors.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import blackjack.domain.MatchRecord;
import blackjack.domain.card.Deck;

public class Users {

    private final List<Player> players;
    private final Dealer dealer;

    private Users(List<Player> players, Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    private Users(List<Player> players) {
        this(players, new Dealer());
    }

    public static Users from(List<String> inputNames) {
        List<Player> players = inputNames.stream()
            .map(Player::new)
            .collect(toList());
        return new Users(players);
    }

    public void drawInitCards(Deck deck) {
        for (Player player : players) {
            player.drawCard(deck);
            player.drawCard(deck);
        }
        dealer.drawCard(deck);
        dealer.drawCard(deck);
    }

    public Map<Player, MatchRecord> createPlayerMatchRecords() {
        return players.stream()
            .collect(toMap(
                Function.identity(),
                player -> MatchRecord.findMatchRecord(player, dealer)
            ));
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public Dealer getDealer() {
        return dealer;
    }
}
