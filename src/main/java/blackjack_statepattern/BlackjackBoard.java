package blackjack_statepattern;

import blackjack_statepattern.participant.Dealer;
import blackjack_statepattern.participant.Player;
import blackjack_statepattern.participant.Players;
import java.util.List;

public final class BlackjackBoard {

    private final Dealer dealer;
    private final Players players;

    public BlackjackBoard(Dealer dealer, Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return players.getPlayers();
    }
}
