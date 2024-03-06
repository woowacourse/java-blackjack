package blackjack.domain.participant;

import blackjack.domain.HandGenerator;
import blackjack.domain.Name;

import java.util.List;

public class Participants {
    private final Dealer dealer;
    private final Players players;

    public Participants(List<Name> playersName, HandGenerator handGenerator) {
        this.players = new Players(playersName, handGenerator);
        this.dealer = new Dealer(handGenerator);
    }
}
