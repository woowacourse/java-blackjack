package blackjack.domain;

import java.util.Collections;
import java.util.List;

public class GameUserStorage {

    private static final String DEALER_NICKNAME = "딜러";
    private List<Player> players;
    private Player dealer;

    public void initialize(List<Nickname> nicknames) {
        players = nicknames.stream()
                .map(Player::new)
                .toList();
        dealer = new Player(new Nickname(DEALER_NICKNAME));
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public Player getDealer() {
        return dealer;
    }
}
