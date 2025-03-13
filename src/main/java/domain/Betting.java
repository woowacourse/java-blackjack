package domain;

import domain.user.User;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Betting {

    private final Map<User, Long> betting;

    public Betting(Map<User, Long> betting) {
        this.betting = betting;
    }

    public Betting(final List<User> users) {
        this.betting = users.stream()
                .collect(
                        Collectors.toMap(
                                user -> user,
                                name -> 0L,
                                (oldValue, newValue) -> oldValue,
                                HashMap::new
                        )
                );
    }


    public Map<User, Long> getBetting() {
        return betting;
    }

    public void summitBet(User user, long submitMoney) {
        betting.put(user, submitMoney);
    }
}
