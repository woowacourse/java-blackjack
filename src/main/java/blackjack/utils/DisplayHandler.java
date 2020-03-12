package blackjack.utils;

import blackjack.domain.User;
import blackjack.domain.Users;

import java.util.List;
import java.util.stream.Collectors;

public class DisplayHandler {
    public static String parseInitialDistribution(Users users) {
        List<User> gameUsers = users.getUsers();
        return gameUsers.stream()
                .map(User::getName)
                .collect(Collectors.joining(", ")) +
                "에게 2장의 카드를 나누었습니다.\n\n" +
                gameUsers.stream()
                        .map(User::showInitialCardInfo)
                        .collect(Collectors.joining("\n"));
    }

    public static String parseFinalResult(Users users) {
        return users.getUsers().stream()
                .map(User::showFinalCardInfo)
                .collect(Collectors.joining("\n"));
    }
}
