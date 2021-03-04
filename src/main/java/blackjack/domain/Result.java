package blackjack.domain;

import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;

import java.util.List;

public class Result {
    private final Dealer dealer;
    private final List<Player> players;

//    Map<User, String>

    public Result(Dealer dealer, List<Player> players) {
        this.dealer = dealer;
        this.players = players;
    }
}
