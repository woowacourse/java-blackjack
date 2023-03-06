package view;

import domain.GameResult;
import domain.card.Card;
import domain.card.Suit;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private static final String WIN = "승 ";
    private static final String DRAW = "무 ";
    private static final String LOSE = "패";
    private static final String SPADE = "스페이드";
    private static final String CLOVER = "클로버";
    private static final String HEART = "하트";
    private static final String DIAMOND = "다이아몬드";
    private static final String DEALER = "딜러";
    private static final String AND = "와 ";
    private static final String CARD = "카드";
    private static final String RESULT = " - 결과";
    private static final String FINAL_RESULT = "## 최종 승패";
    private static final String DEALER_GET_CARD = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String INITIALIZE_HAND = "에게 2장을 나누었습니다.";
    private static final String COLON = ": ";
    private static final String DIVIDE_DELIMITER = ", ";
    private static final StringBuilder message = new StringBuilder();

    public static void printReady(List<String> playerNames) {
        resetMessage();
        String printablePlayerNames = String.join(DIVIDE_DELIMITER, playerNames);
        message.append(System.lineSeparator()).append(DEALER).append(AND);
        message.append(printablePlayerNames).append(INITIALIZE_HAND);
        System.out.println(message);
    }

    public static void printNameAndHand(String name, List<Card> hand) {
        resetMessage();
        String printableHand = hand.stream()
            .map(OutputView::formatCard)
            .collect(Collectors.joining(DIVIDE_DELIMITER));
        message.append(name).append(COLON).append(printableHand);
        System.out.println(message);
    }

    public static void printNameAndHandAndPoint(String name, List<Card> hand, int point) {
        resetMessage();
        String printableHand = hand.stream()
            .map(OutputView::formatCard)
            .collect(Collectors.joining(DIVIDE_DELIMITER));
        message.append(name).append(CARD).append(COLON).append(printableHand);
        message.append(RESULT + COLON).append(point);
        System.out.println(message);
    }

    private static String formatCard(Card card) {
        String denominationName = card.getDenomination().getName();
        String suitName = getSuitName(card.getSuit());
        return denominationName + suitName;
    }

    private static String getSuitName(Suit cardShape) {
        if (cardShape == Suit.SPADE) {
            return SPADE;
        }
        if (cardShape == Suit.CLOVER) {
            return CLOVER;
        }
        if (cardShape == Suit.HEART) {
            return HEART;
        }
        return DIAMOND;
    }

    public static void printDealerReceivedCard() {
        System.out.println(System.lineSeparator() + DEALER_GET_CARD);
    }

    public static void printDealerGameResult(GameResult dealerResult, int playerCount) {
        int winCount = dealerResult.getWinCount();
        int loseCount = dealerResult.getLoseCount();
        int drawCount = playerCount - winCount - loseCount;
        resetMessage();
        message.append(System.lineSeparator()).append(FINAL_RESULT).append(System.lineSeparator());
        message.append(DEALER + COLON);
        message.append(addDealerResultMessage(winCount, drawCount, loseCount));
        System.out.println(message);
    }

    public static void printPlayerBoxResult(String name, GameResult gameResult) {
        resetMessage();
        message.append(name).append(COLON);
        message.append(addPlayerResultMessage(gameResult.getWinCount(), gameResult.getLoseCount()));
        System.out.println(message);
    }

    private static void resetMessage() {
        message.setLength(0);
    }

    private static String addPlayerResultMessage(int winCount, int loseCount) {
        if (winCount == 1) {
            return WIN;
        }
        if (loseCount == 1) {
            return LOSE;
        }
        return DRAW;
    }

    private static String addDealerResultMessage(int winCount, int drawCount, int loseCount) {
        String printResult = "";
        if (winCount > 0) {
            printResult += winCount + WIN;
        }
        if (drawCount > 0) {
            printResult += drawCount + DRAW;
        }
        if (loseCount > 0) {
            printResult += loseCount + LOSE;
        }
        return printResult;
    }

    public static void printLineSeparator() {
        System.out.print(System.lineSeparator());
    }
}
