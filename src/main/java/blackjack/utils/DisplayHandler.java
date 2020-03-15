package blackjack.utils;

import blackjack.domain.user.User;
import blackjack.domain.user.Users;

import java.util.List;
import java.util.stream.Collectors;

public class DisplayHandler {

    public static final String DELIMITER = ", ";
    public static final String INITIAL_CARDS_DISTRIBUTED = "에게 2장의 카드를 나누었습니다.\n\n";
    public static final String NEW_LINE = "\n";

    public static String parseInitialDistribution(Users users) {
        List<User> gameUsers = users.getUsers();
        return joinUserNames(gameUsers) + INITIAL_CARDS_DISTRIBUTED + joinInitialCardNames(gameUsers);
    }

    private static String joinUserNames(List<User> gameUsers) {
        return gameUsers.stream()
                .map(User::getName)
                .collect(Collectors.joining(DELIMITER));
    }

    private static String joinInitialCardNames(List<User> gameUsers) {
        return gameUsers.stream()
                .map(User::showInitialCardNames)
                .collect(Collectors.joining(NEW_LINE));
    }

    public static String parseFinalScoreAnnouncement(Users users) {
        return users.getUsers().stream()
                .map(User::showFinalCardNameScore)
                .collect(Collectors.joining(NEW_LINE));
    }
}
