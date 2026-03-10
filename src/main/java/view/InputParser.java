package view;

import java.util.Arrays;
import java.util.List;

import domain.User;

public class InputParser {

    public static List<User> parseUsers(String input) {
        return Arrays.stream(input.split(","))
                .map(String::strip)
                .map(User::from)
                .toList();
    }
}
