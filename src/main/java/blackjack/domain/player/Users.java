package blackjack.domain.player;

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

    public Map<Name, Integer> getProfits(Dealer dealer) {
        Map<Name, Integer> profits = new LinkedHashMap<>();
        for (User user : users) {
            profits.put(user.getName(), user.getProfit(dealer));
        }
        return profits;
    }

    public List<User> getUsers() {
        return Collections.unmodifiableList(users);
    }
}
