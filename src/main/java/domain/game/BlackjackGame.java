package domain.game;

import domain.user.Dealer;
import domain.user.Participants;
import domain.user.Player;

import java.util.List;

public class BlackjackGame {

    private final Participants participants;
    private final Deck deck;

    public BlackjackGame(Dealer dealer, List<Player> players, Deck deck) {
        this. participants = new Participants(dealer, players);
        this.deck = deck;
    }

    public void hitAll() {
        participants.hitAll(deck);
    }

    public void hitPlayer(String name) {
        participants.hitPlayer(name, deck);
    }

    public boolean hitDealer() {
        return participants.hitDealer(deck);
    }

    public void stayPlayer(String name) {
        participants.stayPlayer(name);
    }

    public boolean isRunning(String name) {
        return participants.isRunning(name);
    }

    public int dealerProfit() {
        return participants.dealerProfit();

    }

    public int profit(Player player) {
        return participants.profit(player);
    }

    public Player findPlayer(String name) {
        return participants.findPlayer(name);
    }

    public Dealer getDealer() {
        return participants.getDealer();
    }

    public List<Player> getPlayers() {
        return participants.getPlayers();
    }
}
