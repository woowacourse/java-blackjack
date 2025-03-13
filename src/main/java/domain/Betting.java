package domain;

import domain.user.User;
import java.util.Map;

public class Betting {

    private final Map<User, Long> betting;

    public Betting(Map<User, Long> betting) {
        this.betting = betting;
    }

    public Map<User, Long> getBetting() {
        return betting;
    }
}
