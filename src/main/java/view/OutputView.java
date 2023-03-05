package view;

import domain.box.BoxResult;
import domain.card.Card;
import domain.card.Suit;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private static final String COLON = ": ";
    private static final String DIVIDE_DELIMITER = ", ";
    private static final String RESULT_MESSAGE = "결과";
    private static final String SCORE_DELIMITER = " - ";
    private static final String WIN_MESSAGE = "승 ";
    private static final String DRAW_MESSAGE = "무 ";
    private static final String LOSE_MESSAGE = "패";
    private static final String DEALER_NAME = "딜러";
    private static final String AND_MESSAGE = "와 ";
    private static final String DEALER_GET_CARD_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String FINAL_RESULT_MESSAGE = "## 최종 승패";
    private static final String GAME_READY_MESSAGE = "에게 2장을 나누었습니다.";
    private static final String SPADE_MESSAGE = "스페이드";
    private static final String CLOVER_MESSAGE = "클로버";
    private static final String HEART_MESSAGE = "하트";
    private static final String DIAMOND_MESSAGE = "다이아몬드";
    private static final StringBuilder message = new StringBuilder();

    public static void printNameHandScore(String name, List<Card> cards, int score) {
        resetMessage();
        message.append(name).append(COLON);
        message.append(cards.stream().map(OutputView::formatCard).collect(Collectors.joining(DIVIDE_DELIMITER)));
        message.append(SCORE_DELIMITER);
        message.append(RESULT_MESSAGE).append(COLON);
        message.append(score);
        System.out.println(message);
    }

    public static void printNameAndHand(String name, List<Card> cards) {
        resetMessage();
        message.append(name).append(COLON);
        message.append(cards.stream().map(OutputView::formatCard).collect(Collectors.joining(DIVIDE_DELIMITER)));
        System.out.println(message);
    }

    private static String formatCard(Card card) {
        String printCards = "";
        printCards += card.getDenomination().getName();
        printCards += getSuitMessage(card.getSuit());
        return printCards;
    }

    private static String getSuitMessage(Suit cardShape) {
        if (cardShape == Suit.SPADE) {
            return SPADE_MESSAGE;
        }
        if (cardShape == Suit.CLOVER) {
            return CLOVER_MESSAGE;
        }
        if (cardShape == Suit.HEART) {
            return HEART_MESSAGE;
        }
        return DIAMOND_MESSAGE;
    }

    public static void printDealerReceivedCard() {
        System.out.println(System.lineSeparator() + DEALER_GET_CARD_MESSAGE);
    }

    public static void printDealerGameResult(BoxResult dealerResult, int playerCount) {
        System.out.println(System.lineSeparator() + FINAL_RESULT_MESSAGE);
        int winCount = dealerResult.getWinCount();
        int loseCount = dealerResult.getLoseCount();
        int drawCount = playerCount - winCount - loseCount;
        resetMessage();
        message.append(DEALER_NAME + COLON);
        message.append(addDealerResultMessage(winCount, drawCount, loseCount));
        System.out.println(message);
    }

    public static void printPlayerResult(String name, BoxResult gameResult) {
        resetMessage();
        message.append(name).append(COLON);
        message.append(addPlayerResultMessage(gameResult.getWinCount(), gameResult.getLoseCount()));
        System.out.println(message);
    }

    public static void printReady(List<String> participantNames) {
        resetMessage();
        message.append(DEALER_NAME + AND_MESSAGE);
        List<String> playerNames = participantNames.subList(1, participantNames.size());
        message.append(String.join(DIVIDE_DELIMITER, playerNames));
        message.append(GAME_READY_MESSAGE).append(System.lineSeparator());
        System.out.println(message);
    }

    private static void resetMessage() {
        message.setLength(0);
    }

    private static String addPlayerResultMessage(int winCount, int loseCount) {
        if (winCount == 1) {
            return WIN_MESSAGE;
        }
        if (loseCount == 1) {
            return LOSE_MESSAGE;
        }
        return DRAW_MESSAGE;
    }

    private static String addDealerResultMessage(int winCount, int drawCount, int loseCount) {
        String printResult = "";
        if (winCount > 0) {
            printResult += winCount + WIN_MESSAGE;
        }
        if (drawCount > 0) {
            printResult += drawCount + DRAW_MESSAGE;
        }
        if (loseCount > 0) {
            printResult += loseCount + LOSE_MESSAGE;
        }
        return printResult;
    }
}
