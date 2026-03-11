package view;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import domain.User;

public class InputParser {

    public static List<String> parseToList(String input) {
        return Arrays.stream(input.split(","))
                .map(String::strip)
                .toList();
    }

    public static List<User> parseToUsers(List<String> names, List<Integer> betAmounts) {
        return IntStream.range(0, names.size())
                .mapToObj(i -> User.from(names.get(i), betAmounts.get(i)))
                .toList();
    }

}
