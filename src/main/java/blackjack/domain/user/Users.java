package blackjack.domain.user;

import static java.util.stream.Collectors.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import blackjack.domain.MatchRecord;
import blackjack.domain.card.Deck;

public class Users {

    private static final int INIT_CARDS_COUNT = 2;

    private final List<Player> players;
    private final Dealer dealer;

    private Users(List<Player> players, Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    private Users(List<Player> players) {
        this(players, new Dealer());
    }

    public static Users from(Map<String, String> inputNameAndMoney) {
        List<Player> players = inputNameAndMoney.entrySet().stream()
            .map(nameMoneyEntry -> Player.fromNameAndMoney(nameMoneyEntry.getKey(), nameMoneyEntry.getValue()))
            .collect(toList());
        return new Users(players);
    }

    public void drawInitCardsPerUsers(Deck deck) {
        for (Player player : players) {
            drawInitCardsPerUsers(deck, player);
        }
        drawInitCardsPerUsers(deck, dealer);
    }

    private void drawInitCardsPerUsers(Deck deck, User user) {
        for (int i = 0; i < INIT_CARDS_COUNT; i++) {
            user.receiveCard(deck.drawCard());
        }
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
