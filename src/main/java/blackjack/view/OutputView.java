package blackjack.view;

import blackjack.domain.user.User;
import blackjack.domain.user.Users;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final String DELIMITER = ", ";
    private static final String NEW_LINE = "\n";

    public static void printInitialCardDistribution(Users users) {
        System.out.println(parseInitialDistribution(users));
    }

    public static void printCardStatus(String cardInfo) {
        System.out.println(cardInfo);
    }

    public static void printBusted(String name) {
        System.out.println(name + "이(가) 버스트 되었습니다");
    }

    public static void printDealerHitMoreCard() {
        System.out.println("딜러는 16이하라 한 장의 카드를 더 받았습니다\n");
    }

    public static void printFinalCardScore(Users users) {
        System.out.println("\n## 결과 : \n" + parseFinalScoreAnnouncement(users) + "\n");
    }

    public static void printFinalResult(String allWinners) {
        System.out.println("## 최종 승패");
        System.out.println(allWinners);
    }

    private static String parseInitialDistribution(Users users) {
        List<User> gameUsers = users.getUsers();
        return gameUsers.stream()
                .map(User::getName)
                .collect(Collectors.joining(DELIMITER)) +
                "에게 2장의 카드를 나누었습니다.\n\n" +
                gameUsers.stream()
                        .map(User::showInitialCardInfo)
                        .collect(Collectors.joining(NEW_LINE));
    }

    private static String parseFinalScoreAnnouncement(Users users) {
        return users.getUsers().stream()
                .map(User::showFinalCardInfo)
                .collect(Collectors.joining(NEW_LINE));
    }
}
