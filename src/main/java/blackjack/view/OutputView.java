package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.scoreboard.GameResult;
import blackjack.domain.scoreboard.ScoreBoard;
import blackjack.domain.scoreboard.WinOrLose;
import blackjack.domain.user.Dealer;
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
    private static final String DEALER_NAME = "딜러";
    private static final long DEFAULT_COUNT = 0L;

    public static void printDrawMessage(List<String> userNames) {
        String names = String.join(DELIMITER, userNames);
        String firstDrawMessage = FIRST_DRAW_MESSAGE_PREFIX + names + FIRST_DRAW_MESSAGE_SUFFIX;
        System.out.println(firstDrawMessage);
    }

    public static void printDealerFirstCard(Card card) {
        String suit = card.getSuit().getLetter();
        String value = card.getValue().getLetter();

        String dealerFirstCard = value + suit;

        System.out.printf(PRINT_CARD_LIST_MSG_FORMAT, DEALER_NAME, dealerFirstCard);
    }

    public static void printCardList(User user) {
        String cards = user.getCards()
                .stream()
                .map(card -> card.getValue().getLetter() + card.getSuit().getLetter())
                .collect(Collectors.joining(DELIMITER));

        System.out.printf(PRINT_CARD_LIST_MSG_FORMAT, user.getName(), cards);
    }

    public static void printCardListAndScore(User user){
        String cards = user.getCards()
                .stream()
                .map(card -> card.getValue().getLetter() + card.getSuit().getLetter())
                .collect(Collectors.joining(DELIMITER));

        System.out.printf(PRINT_CARD_LIST_AND_SCORE_MSG_FORMAT, user.getName(), cards, user.calculateScore());
    }

    public static void printCardListAndScore(Dealer dealer){
        String cards = dealer.getCards()
                .stream()
                .map(card -> card.getValue().getLetter() + card.getSuit().getLetter())
                .collect(Collectors.joining(DELIMITER));

        System.out.printf(PRINT_CARD_LIST_AND_SCORE_MSG_FORMAT, DEALER_NAME, cards, dealer.calculateScore());
    }

    public static void printDealerMoreDrawMessage() {
        System.out.println(DEALER_MORE_DRAW_CARD_MSG);
        println();
    }

    public static void printScoreBoard(ScoreBoard scoreBoard, Dealer dealer){
        Map<User, GameResult> userResults = scoreBoard.getUserResults();
        Set<User> users = userResults.keySet();
        printCardListAndScore(dealer);
        printCardListAndScore(users);
        println();
        printFinalWinOrLose(scoreBoard, userResults, users);
    }

    private static void printCardListAndScore(Set<User> users) {
        users.forEach(OutputView::printCardListAndScore);
    }

    private static void printFinalWinOrLose(ScoreBoard scoreBoard, Map<User, GameResult> userResults, Set<User> users) {
        System.out.println(FINAL_WIN_OR_LOSE_MSG);
        System.out.println(createDealerResultMessage(scoreBoard));

        users.stream()
                .map(user -> user.getName() + COLON + userResults.get(user).getWinOrLose().getCharacter())
                .forEach(System.out::println);
    }

    private static String createDealerResultMessage(ScoreBoard scoreBoard) {
        return DEALER_NAME + COLON + Arrays.stream(WinOrLose.values())
                .map(winOrLose -> scoreBoard.dealerWinOrLoseCounts()
                        .getOrDefault(winOrLose, DEFAULT_COUNT) + winOrLose.getCharacter())
                .collect(Collectors.joining(BLANK));
    }

    public static void println() {
        System.out.println();
    }

}
