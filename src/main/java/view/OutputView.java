package view;

import domain.BoxResult;
import domain.Card;
import domain.Suit;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    public static void printNameHandScore(String name, List<Card> cards, int score) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(name);
        stringBuilder.append(": ");
        stringBuilder.append(cards.stream().map(OutputView::formatCard).collect(Collectors.joining(", ")));
        stringBuilder.append(" - ");
        stringBuilder.append("결과: ");
        stringBuilder.append(score);
        System.out.println(stringBuilder);
    }

    public static void printNameAndHand(String name, List<Card> cards) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(name);
        stringBuilder.append(": ");
        stringBuilder.append(cards.stream().map(OutputView::formatCard).collect(Collectors.joining(", ")));
        System.out.println(stringBuilder);
    }

    private static String formatCard(Card card) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(card.getDenomination().getName());
        stringBuilder.append(formatCardShape(card.getSuit()));
        return stringBuilder.toString();
    }

    private static String formatCardShape(Suit cardShape) {
        if (cardShape == Suit.SPADE) {
            return "스페이드";
        }
        if (cardShape == Suit.CLOVER) {
            return "클로버";
        }
        if (cardShape == Suit.HEART) {
            return "하트";
        }
        return "다이야몬드";
    }

    public static void printDealerReceivedCard() {
        System.out.println(System.lineSeparator() + "딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printDealerGameResult(BoxResult dealerResult, int playerCount) {
        System.out.println(System.lineSeparator() + "## 최종 승패");
        int winCount = dealerResult.getWinCount();
        int loseCount = dealerResult.getLoseCount();
        int drawCount = playerCount - winCount - loseCount;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("딜러: ");
        if (winCount > 0) {
            stringBuilder.append(winCount).append("승 ");
        }
        if (drawCount > 0) {
            stringBuilder.append(drawCount).append("무 ");
        }
        if (loseCount > 0) {
            stringBuilder.append(loseCount).append("패");
        }
        System.out.println(stringBuilder);
    }

    public static void printPlayerResult(String name, BoxResult gameResult) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(name);
        stringBuilder.append(": ");
        int winCount = gameResult.getWinCount();
        if (winCount == 1) {
            stringBuilder.append("승");
        } else if (gameResult.getLoseCount() == 1) {
            stringBuilder.append("패");
        } else {
            stringBuilder.append("무");
        }
        System.out.println(stringBuilder);
    }

    public static void printReady(List<String> participantNames) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("딜러와 ");
        List<String> playerNames = participantNames.subList(1, participantNames.size());
        stringBuilder.append(String.join(", ", playerNames));
        stringBuilder.append("에게 2장을 나누었습니다.");
        stringBuilder.append(System.lineSeparator());
        System.out.println(stringBuilder);
    }
}
