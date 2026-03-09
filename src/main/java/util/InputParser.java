package util;

import domain.User;

import java.util.Arrays;
import java.util.List;

public class InputParser {

    public static List<User> parseUsers(String input) {
        return Arrays.stream(input.split(","))
                .map(User::new)
                .toList();
    }
}
