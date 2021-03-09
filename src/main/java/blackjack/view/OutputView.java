package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.scoreboard.DealerGameResult;
import blackjack.domain.scoreboard.UserGameResult;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Participant;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final String DELIMITER = ", ";
    private static final String BLANK = " ";
    private static final String COLON = ": ";
    private static final String FIRST_DRAW_MESSAGE_PREFIX = "딜러와 ";
    private static final String FIRST_DRAW_MESSAGE_SUFFIX = "에게 2장의 카드를 나누었습니다.";
    private static final String PRINT_CARD_LIST_MSG_FORMAT = "%s 카드: %s%n";
    private static final String PRINT_CARD_LIST_AND_SCORE_MSG_FORMAT = "%s 카드: %s - 결과 : %d%n";
    private static final String FINAL_WIN_OR_LOSE_MSG = "##최종 승패";
    private static final String DEALER_MORE_DRAW_CARD_MSG = "딜러는 16이하라 한장의 카드를 더 받았습니다.";

    public static void printDrawMessage(List<String> userNames) {
        String names = String.join(DELIMITER, userNames);
        String firstDrawMessage = FIRST_DRAW_MESSAGE_PREFIX + names + FIRST_DRAW_MESSAGE_SUFFIX;
        System.out.println(firstDrawMessage);
    }

    public static void printDealerFirstCard(Dealer dealer) {
        String dealerFirstCard = dealer.getFirstCard().getLetterOfValueAndSuit();

        System.out.printf(PRINT_CARD_LIST_MSG_FORMAT, dealer.getName(), dealerFirstCard);
    }

    public static void printCardList(Participant participant) {
        String cards = participant.getCards()
                .stream()
                .map(Card::getLetterOfValueAndSuit)
                .collect(Collectors.joining(DELIMITER));

        System.out.printf(PRINT_CARD_LIST_MSG_FORMAT, participant.getName(), cards);
    }

    public static void printCardListAndScore(Participant participant) {
        String cards = participant.getCards()
                .stream()
                .map(Card::getLetterOfValueAndSuit)
                .collect(Collectors.joining(DELIMITER));

        System.out.printf(PRINT_CARD_LIST_AND_SCORE_MSG_FORMAT, participant.getName(), cards, participant.calculateScore());
    }

    public static void printDealerMoreDrawMessage() {
        System.out.println(DEALER_MORE_DRAW_CARD_MSG);
        println();
    }

    public static void printFinalDealerWinOrLose(DealerGameResult dealerGameResult, UserGameResult userGameResult) {
        System.out.println(FINAL_WIN_OR_LOSE_MSG);
        System.out.println(createDealerResultMessage(dealerGameResult, userGameResult));
    }

    private static String createDealerResultMessage(DealerGameResult dealerGameResult, UserGameResult userGameResult) {

        return dealerGameResult.getDealerName() + COLON + makeDealersFinalWinOrLose(dealerGameResult, userGameResult);
    }

    private static String makeDealersFinalWinOrLose(DealerGameResult dealerGameResult, UserGameResult userGameResult) {

        return String.join(BLANK, dealerGameResult.countDealerWinOrLose(userGameResult));
    }

    public static void printFinalUserWinOrLose(UserGameResult userGameResult) {
        userGameResult.getResultEntrySet().stream()
                .map(result -> result.getKey().getName() + COLON + result.getValue().getCharacter())
                .forEach(System.out::println);
    }

    public static void println() {
        System.out.println();
    }

    public static void printMessage(String message) {
        System.out.println(message);

    }
}
