package blackjack.model.player;

import java.util.List;

public class Players {

    private final Player dealer;
    private final List<Player> users;

    public Players(final Player dealer, final List<Player> users) {
        this.dealer = dealer;
        this.users = users;
    }

    public List<Player> getUsers() {
        return users;
    }

}
