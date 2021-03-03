package blackjack.domain;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Users {
    private final List<User> users;

    public Users(List<User> users) {
        this.users = new ArrayList<>(users);
    }

    public Map<Name, ResultType> getResult(Dealer dealer) {
        Map<Name, ResultType> result = new LinkedHashMap<>();
        for (User user : users) {
            result.put(user.getName(), user.getResult(dealer));
        }
        return result;
    }
}
