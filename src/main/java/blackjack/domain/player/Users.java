package blackjack.domain.player;

import blackjack.domain.ResultType;
import blackjack.domain.card.Cards;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Users {
    private static final String DELIMITER = ",";
    private final List<User> users;

    public Users(List<User> users) {
        this.users = new ArrayList<>(users);
    }

    public Users(String usersNames) {
        users = Arrays.stream(usersNames.split(DELIMITER))
            .map(User::new)
            .collect(Collectors.toList());
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
