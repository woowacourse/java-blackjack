package model.rounds;

import java.util.ArrayList;
import java.util.List;
import model.bettings.Wager;
import model.cards.Hand;
import model.participants.Dealer;
import model.participants.Name;
import model.participants.Player;
import model.participants.Players;

public class Actors {
    private static final int INDEX_START = 0;

    private Dealer dealer;
    private Players players;

    public void inviteDealer() {
        this.dealer = new Dealer();
        this.dealer.initializeHand();
    }

    public void invitePlayers(Dealer dealer, List<String> playerNames, List<Integer> playerWagers) {
        List<Player> players = new ArrayList<>();
        for (int i = INDEX_START; i < playerNames.size(); i++) {
            String name = playerNames.get(i);
            int wager = playerWagers.get(i);
            Hand hand = dealer.produceHand();
            players.add(new Player(new Name(name), new Wager(wager), hand));
        }
        this.players = new Players(players);
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Players getPlayers() {
        return players;
    }
}
