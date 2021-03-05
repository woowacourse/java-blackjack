package blackjack.domain.game;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.List;

public class BlackJackInitializer {

    private final Dealer dealer;
    private final List<Player> players;

    public BlackJackInitializer(Dealer dealer, List<Player> players) {
        this.dealer = dealer;
        this.players = players;
    }

    public void setBaseCardToPlayers() {
        dealer.setBaseCard();
        dealer.setPlayersBaseCard(players);
    }
}
