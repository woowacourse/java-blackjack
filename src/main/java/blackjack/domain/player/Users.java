package blackjack.domain.player;

import blackjack.domain.ResultType;
import blackjack.domain.card.Cards;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Users {
    private final List<User> users;

    public Users(List<User> users) {
        this.users = new ArrayList<>(users);
    }

    public void drawRandomTwoCards(Cards allCards) {
        users.forEach(user -> user.drawRandomTwoCards(allCards));
    }

    public Map<Name, ResultType> getResult(Dealer dealer) {
        Map<Name, ResultType> result = new LinkedHashMap<>();
        for (User user : users) {
            result.put(user.getName(), user.getResult(dealer));
        }
        return result;
    }

    public List<User> getUsers() {
        return Collections.unmodifiableList(users);
    }
}
