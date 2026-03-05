package blackjack.util;

import blackjack.domain.User;
import java.util.ArrayList;
import java.util.List;

public class InputParser {

    private InputParser() {
    }

    public static List<User> createUser(String userName) {
        String[] userNames = userName.split(",");

        List<User> users = new ArrayList<>();
        for (String name : userNames) {
            users.add(new User(name));
        }

        return users;
    }

}
