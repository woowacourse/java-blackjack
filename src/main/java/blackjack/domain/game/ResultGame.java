package blackjack.domain.game;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;

import java.util.List;

public class ResultGame {

    private final Dealer dealer;
    private final List<Player> players;

    public ResultGame(final Participants participants) {
        this.dealer = participants.getDealer();
        this.players = participants.getPlayers();

    }

    public void calculateResult() {
        players.forEach(this::put);
    }

    private void put(Player player) {
        player.setRate(dealer.compareScoreWith(player).rate());
    }
}
