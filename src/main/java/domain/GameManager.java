package domain;

import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import java.util.Collections;
import java.util.List;

public class GameManager {

    private static final int INITIAL_CARDS = 2;
    public static final int DEALER_HIT_MIN_THRESHOLD = 16;

    private final Dealer dealer;
    private final Players players;

    private GameManager(Dealer dealer, Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public static GameManager of(Dealer dealer, Players players) {
        return new GameManager(dealer, players);
    }

    public void distributeCards() {
        for (int count = 0; count < INITIAL_CARDS; count++) {
            players.receiveCards(dealer);
            dealer.receive();
        }
    }

    public void passCardToPlayer(String name) {
        Player player = players.findByName(name);
        player.receive(dealer.drawCard());
    }

    public boolean passCardToDealer() {
        if (dealer.getScore() > DEALER_HIT_MIN_THRESHOLD) {
            return false;
        }
        dealer.receive();
        return true;
    }

    public int getScoreOf(String name) {
        Player player = players.findByName(name);
        return player.getScore();
    }

    public List<String> getPlayersName() {
        return Collections.unmodifiableList(players.getPlayersName());
    }

    public Player getPlayerByName(String name) {
        return players.findByName(name);
    }
}
