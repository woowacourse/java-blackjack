package blackjack.model.gamer;

import java.util.List;

public class Gamers {

    private final Dealer dealer;
    private final List<Player> players;

    public Gamers(Dealer dealer, List<Player> players) {
        validatePlayerExists(players);
        validateDealerExists(dealer);
        this.dealer = dealer;
        this.players = players;
    }

    private void validatePlayerExists(List<Player> players) {
        if (players.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 등록할 플레이어가 없습니다.");
        }
    }

    private void validateDealerExists(Dealer dealer) {
        if (dealer == null) {
            throw new IllegalArgumentException("[ERROR] 등록할 딜러가 없습니다.");
        }
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
