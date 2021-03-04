package blackjack.domain;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import java.util.List;

public class BlackjackManager {

    private final Dealer dealer;
    private final Players players;

    public BlackjackManager(Dealer dealer, Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public void initGame() {
        players.initTwoCardsByDealer(dealer);
    }

    public List<Player> getPlayers() {
        return players.toList();
    }
}
