package blackjack.domain.dto;

import blackjack.domain.user.Dealer;
import blackjack.domain.user.Players;

public class ResponseInitHandDto {

    private final Dealer dealer;
    private final Players players;

    public ResponseInitHandDto(Dealer dealer, Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Players getPlayers() {
        return players;
    }
}
