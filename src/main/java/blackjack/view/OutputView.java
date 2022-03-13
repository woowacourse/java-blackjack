package blackjack.view;

import blackjack.domain.DistributeResult;
import blackjack.domain.card.Card;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.User;
import blackjack.domain.participant.Participants;
import blackjack.domain.result.DealerResult;
import blackjack.domain.result.Result;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.System.lineSeparator;

public class OutputView {

    private static final String INIT_DISTRIBUTE_FORMAT = "딜러와 %s에게 2장의 카드를 나누었습니다.";
    private static final String MORE_DEALER_DRAW_CARD = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String CARD_SEPARATOR = ", ";
    private static final String USER_CARD_FORMAT = "%s: %s";
    private static final String DEALER_CARD_FORMAT = "딜러: %s";
    private static final String SCORE_FORMAT = " - 점수 : %d";
    private static final String FINAL_RESULT_MESSAGE = "## 최종 승패";
    private static final String DEALER_RESULT_FORMAT = "딜러: %d승 %d무 %d패";
    private static final String USER_RESULT_FORMAT = "%s: %s";
    public static final int DEALER_HIDE_INDEX = 0;

//    public static void printInitDistribute(Participants users, Dealer dealer) {
//        System.out.printf(lineSeparator() + INIT_DISTRIBUTE_FORMAT, String.join(CARD_SEPARATOR, users.getUserNames()));
//        printDealerData(dealer);
//
//        for (User user : users.getUsers()) {
//            printUserData(user);
//        }
//        System.out.print(lineSeparator());
//    }

    private static void printDealerData(Dealer dealer) {
        System.out.printf(lineSeparator() + DEALER_CARD_FORMAT + lineSeparator(),
                getHoldingCards(dealer.getCards().subList(DEALER_HIDE_INDEX + 1, dealer.getCards().size())));
    }

    public static void printUserData(User user) {
        System.out.printf(USER_CARD_FORMAT + lineSeparator(), user.getName(), getHoldingCards(user.getCards()));
    }

    private static String getHoldingCards(List<Card> cards) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Card card : cards) {
            stringBuilder.append(card.getOriginalNumber())
                    .append(card.getCardType())
                    .append(CARD_SEPARATOR);
        }
        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        return stringBuilder.toString();
    }

    public static void printDealerDraw() {
        System.out.println(lineSeparator() + MORE_DEALER_DRAW_CARD);
    }

    public static void printFinalCard(Participants users, Dealer dealer) {
        printDealerDataContainsScore(dealer);
        for (User user : users.getUsers()) {
            printUserDataContainsScore(user);
        }
    }

    private static void printDealerDataContainsScore(Dealer dealer) {
        System.out.printf(lineSeparator() + DEALER_CARD_FORMAT + SCORE_FORMAT + lineSeparator(),
                getHoldingCards(dealer.getCards()), dealer.getCardSum());
    }

    private static void printUserDataContainsScore(User user) {
        System.out.printf(USER_CARD_FORMAT + SCORE_FORMAT, user.getName(), getHoldingCards(user.getCards()), user.getCardSum());
        System.out.print(lineSeparator());
    }

    public static void printFinalScore(DealerResult result, Participants users, int dealerSum) {
        printDealerResult(result.getCount());

        for (User user : users.getUsers()) {
            printUserResult(user, user.checkResult(dealerSum));
        }
    }

    private static void printDealerResult(Map<Result, Integer> count) {
        System.out.println(lineSeparator() + FINAL_RESULT_MESSAGE);
        System.out.printf(DEALER_RESULT_FORMAT, count.getOrDefault(Result.WIN, 0),
                count.getOrDefault(Result.DRAW, 0), count.getOrDefault(Result.LOSE, 0));
        System.out.print(lineSeparator());
    }

    private static void printUserResult(User user, Result result) {
        System.out.printf(USER_RESULT_FORMAT + lineSeparator(), user.getName(), result.getName());
    }

    public static void printInitDistribute(List<DistributeResult> distributeResults) {
        List<String> userNames = getUserNameExcludeDealer(distributeResults);
        System.out.println(MessageFormat.format("딜러와 {0}에게 {1}장을 나누었습니다.",
                String.join(", ", userNames),
                distributeResults.size()));
        for (DistributeResult distributeResult : distributeResults) {
            System.out.println(distributeResult.getConcatNameAndCardsExcludeOneCard());
        }
    }

    private static List<String> getUserNameExcludeDealer(List<DistributeResult> distributeResults) {
        List<String> userNames = distributeResults.stream()
                .map(DistributeResult::getName)
                .skip(1)
                .collect(Collectors.toList());
        return userNames;
    }
}
