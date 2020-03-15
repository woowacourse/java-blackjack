package blackjack.utils;

import blackjack.domain.card.Deck;
import blackjack.domain.user.Player;
import blackjack.domain.user.User;
import blackjack.domain.user.Users;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class InputHandler {

    public static final String SPACE = " ";
    public static final String BLANK = "";
    public static final String DELIMITER = ",";
    public static final String NAME_SHOULD_BE_PRESENT = "이름은 최소 1개 이상이어야 합니다";
    public static final String NAME_DUPLICATED = "중복된 이름이 있습니다";

    public static List<String> parseName(String input) {
        validateNullOrEmptyName(input);
        List<String> names = Arrays.asList(splitNames(input));
        validateDuplicatedName(names);
        return names;
    }

    private static String[] splitNames(String input) {
        return input.trim()
                .replace(SPACE, BLANK)
                .split(DELIMITER);
    }

    private static void validateNullOrEmptyName(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException(NAME_SHOULD_BE_PRESENT);
        }
    }

    private static void validateDuplicatedName(List<String> names) {
        if (names.size() != new HashSet<>(names).size()) {
            throw new IllegalArgumentException(NAME_DUPLICATED);
        }
    }

    public static void hitMoreCard(Users users, Deck deck) {
        List<User> gameUsers = users.getUsers();
        gameUsers.stream()
                .filter(user -> user instanceof Player)
                .forEach(user -> askForHit(deck, user));
    }

    private static void askForHit(Deck deck, User user) {
        while (InputView.askForHit(user.getName())) {
            user.receiveCard(deck.draw());
            OutputView.printCardStatus(user.showCardInfo());
            if (user.isBusted()) {
                OutputView.printBusted(user.getName());
                break;
            }
        }
    }
}
