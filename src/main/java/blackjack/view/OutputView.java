package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.scoreboard.UserGameResult;
import blackjack.domain.scoreboard.ScoreBoard;
import blackjack.domain.scoreboard.WinOrLose;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Participant;
import blackjack.domain.user.User;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class OutputView {
    private static final String DELIMITER = ", ";
    private static final String BLANK = " ";
    private static final String COLON = ": ";
    private static final String FIRST_DRAW_MESSAGE_PREFIX = "딜러와 ";
    private static final String FIRST_DRAW_MESSAGE_SUFFIX = "에게 2장의 카드를 나누었습니다.";
    private static final String PRINT_CARD_LIST_MSG_FORMAT = "%s 카드: %s%n";
    private static final String PRINT_CARD_LIST_AND_SCORE_MSG_FORMAT = "%s 카드: %s - 점수 : %d%n";
    private static final String FINAL_WIN_OR_LOSE_MSG = "##최종 승패";
    private static final String DEALER_MORE_DRAW_CARD_MSG = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final long DEFAULT_COUNT = 0L;

    public static void printDrawMessage(List<String> userNames) {
        String names = String.join(DELIMITER, userNames);
        String firstDrawMessage = FIRST_DRAW_MESSAGE_PREFIX + names + FIRST_DRAW_MESSAGE_SUFFIX;
        System.out.println(firstDrawMessage);
    }

    public static void printDealerFirstCard(Card card) {
        String dealerFirstCard = card.getLetterOfValueAndSuit();

        System.out.printf(PRINT_CARD_LIST_MSG_FORMAT, Dealer.DEALER_NAME.getName(), dealerFirstCard);
    }

    public static void printCardList(Participant participant) {
        String cards = participant.getCards()
                .stream()
                .map(Card::getLetterOfValueAndSuit)
                .collect(Collectors.joining(DELIMITER));

        System.out.printf(PRINT_CARD_LIST_MSG_FORMAT, participant.getName(), cards);
    }

    public static void printCardListAndScore(Participant participant){
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

    public static void printScoreBoard(ScoreBoard scoreBoard, Dealer dealer){
        Set<User> users = scoreBoard.getUserResults().keySet();

        printCardListAndScore(dealer);
        printCardListAndScore(users);
        println();

        printFinalWinOrLose(scoreBoard, scoreBoard.getUserResults(), users);
    }

    private static void printCardListAndScore(Set<User> users) {
        users.forEach(OutputView::printCardListAndScore);
    }

    private static void printFinalWinOrLose(ScoreBoard scoreBoard, Map<User, UserGameResult> userResults, Set<User> users) {
        System.out.println(FINAL_WIN_OR_LOSE_MSG);
        System.out.println(createDealerResultMessage(scoreBoard));

        users.stream()
                .map(user -> user.getName() + COLON + userResults.get(user).getWinOrLose().getCharacter())
                .forEach(System.out::println);
    }

    private static String createDealerResultMessage(ScoreBoard scoreBoard) {
        return Dealer.DEALER_NAME.getName() + COLON + Arrays.stream(WinOrLose.values())
                .map(winOrLose -> scoreBoard.dealerWinOrLoseCounts()
                        .getOrDefault(winOrLose, DEFAULT_COUNT) + winOrLose.getCharacter())
                .collect(Collectors.joining(BLANK));
    }

    public static void println() {
        System.out.println();
    }

    public static void printMessage(String message) {
        System.out.println(message);
    }

}
