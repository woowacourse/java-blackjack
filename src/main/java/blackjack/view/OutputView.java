package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.scoreboard.GameResult;
import blackjack.domain.scoreboard.ScoreBoard;
import blackjack.domain.scoreboard.UserGameResult;
import blackjack.domain.scoreboard.WinOrLose;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Participant;
import blackjack.domain.user.User;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
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

    public static void printFinalWinOrLose(ScoreBoard scoreBoard, Map<User, UserGameResult> userResult) {
        System.out.println(FINAL_WIN_OR_LOSE_MSG);
        System.out.println(createDealerResultMessage(scoreBoard));

        userResult.entrySet().stream()
                .map(result -> result.getKey().getName() + COLON + result.getValue().getStringWinOrLose())
                .forEach(System.out::println);
    }

    private static String createDealerResultMessage(ScoreBoard scoreBoard) {
        return Dealer.DEALER_NAME.getName() + COLON + makeDealersFinalWinOrLose(scoreBoard);
    }

    private static String makeDealersFinalWinOrLose(ScoreBoard scoreBoard) {
        return Arrays.stream(WinOrLose.values())
                .map(winOrLose -> scoreBoard.countDealerWinOrLose(winOrLose) + winOrLose.getCharacter())
                .collect(Collectors.joining(BLANK));
    }

    public static void println() {
        System.out.println();
    }

    public static void printMessage(String message) {
        System.out.println(message);
    }

    public static void printCardListAndScore2(GameResult dealerGameResult) {
        String cards = dealerGameResult.getResultCards().stream()
                .map(Card::getLetterOfValueAndSuit)
                .collect(Collectors.joining(DELIMITER));

        System.out.printf(PRINT_CARD_LIST_AND_SCORE_MSG_FORMAT, dealerGameResult.getName(), cards, dealerGameResult.calculateScore());
    }
}
