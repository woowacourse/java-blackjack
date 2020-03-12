package factory;

import domain.user.User;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class UserFactory {
    private static final String NAME_DELIMITER = ",";

    private UserFactory() {
    }

    public static List<User> create(final String userInput) {
        List<User> users = Arrays.stream(userInput.split(NAME_DELIMITER))
                .map(User::new)
                .collect(Collectors.toList());
        return Collections.unmodifiableList(users);
    }
}
