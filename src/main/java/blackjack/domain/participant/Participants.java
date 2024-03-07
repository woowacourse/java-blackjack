package blackjack.domain.participant;

import blackjack.domain.card.HandGenerator;
import java.util.List;

public class Participants {
    private final Dealer dealer;
    private final Players players;

    public Participants(List<Name> playersName, HandGenerator handGenerator) {
        this.players = new Players(playersName, handGenerator);
        this.dealer = new Dealer(handGenerator);
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Players getPlayers() {
        return players;
    }
}
