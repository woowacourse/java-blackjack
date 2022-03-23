package blackjack.domain.dto;

import blackjack.domain.user.Players;
import blackjack.domain.user.User;

public class ResponseInitHandDto {

    private final User dealer;
    private final Players players;

    public ResponseInitHandDto(User dealer, Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public User getDealer() {
        return dealer;
    }

    public Players getPlayers() {
        return players;
    }
}
