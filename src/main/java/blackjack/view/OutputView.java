package blackjack.view;

import blackjack.domain.Player;
import blackjack.domain.ResultType;
import blackjack.domain.User;
import blackjack.domain.Users;
import blackjack.domain.card.Card;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private static final String COMMA_WITH_BLANK = ", ";

    public static void printInitialComment(Users users) {
        System.out.printf("%s와 %s에게 2장의 카드를 나누어주었습니다.\n", users.getDealer().getName(), createPlayersCardStringFormat(users.getPlayers()));
        for (User user : users.gerUsers()) {
            System.out.println(makeCardsStringFormat(user));
        }
    }

    public static void printCardsOfPlayersWithScore(Users users) {
        for (User user : users.gerUsers()) {
            System.out.print(makeCardsStringFormat(user) + " - 결과: " + makeResultComment(user.getScore()) + "\n");
        }
    }

    private static String makeResultComment(int score) {
        if (score == Card.BLACKJACK_SCORE) {
            return "블랙잭";
        }
        if (score == Card.BUST) {
            return "버스트";
        }
        return Integer.toString(score);
    }

    public static void printCardsOfPlayer(Player player) {
        System.out.println(makeCardsStringFormat(player));
    }


    public static String makeCardsStringFormat(User user) {
        return String.format("%s 카드 : %s", user.getName(), createCardsStringFormat(user.getCards()));
    }

    private static String createCardsStringFormat(List<Card> cards) {
        return cards.stream()
                .map(Card::toString)
                .collect(Collectors.joining(COMMA_WITH_BLANK));
    }

    private static String createPlayersCardStringFormat(List<Player> players) {
        return players.stream()
                .map(User::getName)
                .collect(Collectors.joining(COMMA_WITH_BLANK));
    }

    public static void printResult(Map<User, ResultType> checkWinOrLose) {
        System.out.println("## 최종 승패");

        printDealerResult(checkWinOrLose);

        for (User user : checkWinOrLose.keySet()) {
            String temp = checkWinOrLose.get(user).getName();
            System.out.printf("%s: %s\n", user.getName(), temp);
        }
    }

    private static void printDealerResult(Map<User, ResultType> checkWinOrLose) {
        long loseCount = checkWinOrLose.values().stream()
                .filter(item -> item == ResultType.WIN)
                .count();

        long winCount = checkWinOrLose.values().stream()
                .filter(item -> item == ResultType.LOSE)
                .count();

        long drawCount = checkWinOrLose.values().stream()
                .filter(item -> item == ResultType.DRAW)
                .count();

        System.out.printf("딜러: %d승 %d무 %d패 \n", winCount, drawCount, loseCount);
    }


}
