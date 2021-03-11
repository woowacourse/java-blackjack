package blackjack.view;

import blackjack.domain.ResultType;
import blackjack.domain.card.Cards;
import blackjack.domain.user.*;
import blackjack.domain.card.Card;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private static final String COMMA_WITH_BLANK = ", ";
    private static final int INDEX_ZERO = 0;

    public static void printError(Exception e) {
        System.out.println(e.getMessage());
    }

    public static void printInitialComment(Dealer dealer, Players players) {
        System.out.printf("%s와 %s에게 2장의 카드를 나누어주었습니다.\n", dealer.getName(), createUsersCardStringFormat(players.players()));
    }

    public static void printCardsOfDealerWithOneCardOpened(Dealer dealer) {
        System.out.printf("%s 카드: %s\n", dealer.getName(), makeCardStringFormat(dealer.getCards().cards().get(INDEX_ZERO)));
    }

    private static String makeCardStringFormat(Card card) {
        return String.format("%s%s", card.getDenominationName(), card.getSuitName());
    }

    public static void printCardsOfPlayersWithoutScore(Players players) {
        for (User user : players.players()) {
            System.out.print(makeCardsStringFormat(user) + "\n");
        }
        System.out.println();
    }

    public static String makeCardsStringFormat(User user) {
        return String.format("%s 카드 : %s", user.getName(), makeCardsStringFormat(user.getCards()));
    }

    private static String makeCardsStringFormat(Cards cards) {
        return cards.cards().stream()
                .map(OutputView::makeCardStringFormat)
                .collect(Collectors.joining(COMMA_WITH_BLANK));
    }

    public static void printCardsOfUsersWithScore(Users users) {
        for (User user : users.users()) {
            System.out.print(makeCardsStringFormat(user) + " - 결과: " + makeResultComment(user.getCards()) + "\n");
        }
        System.out.println();
    }

    private static String makeResultComment(Cards cards) {
        if (cards.isBlackJack()) {
            return "블랙잭";
        }
        if (cards.isBust()) {
            return "버스트";
        }
        return Integer.toString(cards.getScore());
    }

    public static void printCardsOfUser(User user) {
        System.out.println(makeCardsStringFormat(user));
    }


    private static String createUsersCardStringFormat(List<? extends User> users) {
        return users.stream()
                .map(User::getName)
                .collect(Collectors.joining(COMMA_WITH_BLANK));
    }

    public static void printResult(Map<User, ResultType> resultMap) {
        System.out.println("## 최종 승패");

        printDealerResult(resultMap);

        for (User user : resultMap.keySet()) {
            String result = resultMap.get(user).getName();
            System.out.printf("%s: %s\n", user.getName(), result);
        }
    }

    private static void printDealerResult(Map<User, ResultType> resultMap) {
        Map<ResultType, Integer> countMap = new HashMap<>();
        Arrays.stream(ResultType.values())
                .forEach(value -> countMap.put(value, 0));

        resultMap.values()
                .forEach(value -> countMap.put(value, countMap.get(value) + 1));

        System.out.printf("딜러: %d승 %d무 %d패 \n",
                countMap.get(ResultType.LOSE),
                countMap.get(ResultType.DRAW),
                countMap.get(ResultType.WIN)
        );
    }

    public static void printDealerGetNewCardsMessage() {
        System.out.println("딜러는 16이하라 한 장의 카드를 더 받았습니다.");
    }
}
