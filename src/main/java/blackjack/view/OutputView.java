package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.result.DealerResult;
import blackjack.domain.result.DistributeResult;
import blackjack.domain.result.Result;
import blackjack.domain.result.UserResult;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.System.lineSeparator;

public class OutputView {

    private static final String INIT_DISTRIBUTE_FORMAT = "딜러와 {0}에게 {1}장을 나누었습니다.";
    private static final String MORE_DEALER_DRAW_CARD = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String FINAL_RESULT_MESSAGE = "## 최종 승패";
    private static final String DEALER_RESULT_FORMAT = "딜러: {0}승 {1}무 {2}패";

    private static final String DISPLAY_DELIMITER = ", ";
    private static final String DEALER_NAME = "딜러";
    private static final int NOT_CONTAIN_INDEX = 0;
    private static final String NAME_SEPARATOR = ":";
    private static final String USER_RESULT_FORMAT = "{0}: {1}";
    private static final String CARD_SUFFIX = " 카드 ";
    private static final String RESULT_PREFIX = " - 결과: ";
    private static final int DEALER_EXCLUDE = 1;

    public static void printInitDistribute(List<DistributeResult> distributeResults) {
        List<String> userNames = getUserNameExcludeDealer(distributeResults);
        System.out.println(MessageFormat.format(INIT_DISTRIBUTE_FORMAT,
                String.join(", ", userNames),
                distributeResults.size()));
        for (DistributeResult distributeResult : distributeResults) {
            System.out.println(getConcatNameAndCardsDealerExcludeOneCard(distributeResult));
        }
    }

    public static void printUserData(DistributeResult distributeResult) {
        System.out.println(getConcatNameAndCardNames(distributeResult));
    }

    public static void printDealerDraw() {
        System.out.println(lineSeparator() + MORE_DEALER_DRAW_CARD);
    }

    public static void printFinalCardWithScore(List<DistributeResult> distributeResults) {
        System.out.println(System.lineSeparator());
        for (DistributeResult distributeResult : distributeResults) {
            System.out.println(getConcatNameAndCardsIncludeSum(distributeResult));
        }
    }

    public static void printFinalResult(DealerResult dealerResult, List<UserResult> userResults) {
        System.out.println(System.lineSeparator());
        System.out.println(FINAL_RESULT_MESSAGE);
        printDealerResult(dealerResult);
        printUserResult(userResults);
    }

    private static List<String> getUserNameExcludeDealer(List<DistributeResult> distributeResults) {
        List<String> userNames = distributeResults.stream()
                .map(DistributeResult::getName)
                .skip(DEALER_EXCLUDE)
                .collect(Collectors.toList());
        return userNames;
    }

    private static void printUserResult(List<UserResult> userResults) {
        for (UserResult userResult : userResults) {
            System.out.println(MessageFormat.format(USER_RESULT_FORMAT,
                    userResult.getUserName(), userResult.getResult()));
        }
    }

    private static void printDealerResult(DealerResult dealerResult) {
        Map<Result, Integer> count = dealerResult.getCount();
        System.out.println(MessageFormat.format(DEALER_RESULT_FORMAT, count.getOrDefault(Result.WIN, 0),
                count.getOrDefault(Result.DRAW, 0), count.getOrDefault(Result.LOSE, 0)));
    }


    private static String getConcatNameAndCardNames(DistributeResult distributeResult) {
        return distributeResult.getName() + NAME_SEPARATOR + distributeResult.getCards().stream()
                .map(Card::getCardText)
                .collect(Collectors.joining(DISPLAY_DELIMITER));
    }

    private static String getConcatNameAndCardsDealerExcludeOneCard(DistributeResult distributeResult) {
        String name = distributeResult.getName();
        List<Card> cards = distributeResult.getCards();
        if (name.equals(DEALER_NAME))
            return name + NAME_SEPARATOR + cards.subList(NOT_CONTAIN_INDEX + 1,
                    cards.size()).stream().map(Card::getCardText).collect(Collectors.joining(DISPLAY_DELIMITER));
        return getConcatNameAndCardNames(distributeResult);
    }

    private static String getConcatNameAndCardsIncludeSum(DistributeResult distributeResult) {
        String name = distributeResult.getName();
        List<Card> cards = distributeResult.getCards();
        int cardSum = distributeResult.getCardSum();
        return name + CARD_SUFFIX + NAME_SEPARATOR + cards.stream()
                .map(Card::getCardText)
                .collect(Collectors.joining(DISPLAY_DELIMITER)) + RESULT_PREFIX + cardSum;
    }
}
