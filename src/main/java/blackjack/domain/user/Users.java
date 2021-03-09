package blackjack.domain.user;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Users {
    private final List<User> users = new ArrayList<>();

    public Users(Dealer dealer, Players players) {
        this.users.add(dealer);
        this.users.addAll(new ArrayList<>(players.players()));
    }

    public List<User> users() {
        return Collections.unmodifiableList(this.users);
    }
}
