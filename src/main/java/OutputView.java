import card.Card;
import card.Suit;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private static final String SPADE = "스페이드";
    private static final String CLOVER = "클로버";
    private static final String HEART = "하트";
    private static final String DIAMOND = "다이아몬드";
    private static final String DEALER = "딜러";
    private static final String AND = "와 ";
    private static final String CARD = "카드";
    private static final String RESULT = " - 결과";
    private static final String FINAL_RESULT = "## 최종 수익";
    private static final String DEALER_GET_CARD = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String INITIALIZE_HAND = "에게 2장을 나누었습니다.";
    private static final String COLON = ": ";
    private static final String DIVIDE_DELIMITER = ", ";
    private static final StringBuilder message = new StringBuilder();

    private static void resetMessage() {
        message.setLength(0);
    }

    public static void printReady(List<String> playerNames) {
        resetMessage();
        String printablePlayerNames = String.join(DIVIDE_DELIMITER, playerNames);
        message.append(System.lineSeparator()).append(DEALER).append(AND);
        message.append(printablePlayerNames).append(INITIALIZE_HAND);
        System.out.println(message);
    }

    public static void printDealerReadyStatus(List<Card> dealerHand) {
        printNameAndHand(DEALER, List.of(dealerHand.get(0)));
    }

    public static void printPlayerReadyStatus(String playerName, List<Card> playerHand) {
        printNameAndHand(playerName, playerHand);
    }

    private static void printNameAndHand(String name, List<Card> hand) {
        resetMessage();
        String printableHand = hand.stream()
            .map(OutputView::formatCard)
            .collect(Collectors.joining(DIVIDE_DELIMITER));
        message.append(name).append(COLON).append(printableHand);
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

    public static void printDealerNameAndHandAndPoint(List<Card> dealerHand, int dealerPoint) {
        printNameAndHandAndPoint(DEALER, dealerHand, dealerPoint);
    }

    public static void printPlayerNameAndHandAndPoint(String playerName, List<Card> playerHand, int playerPoint) {
        printNameAndHandAndPoint(playerName, playerHand, playerPoint);
    }

    private static void printNameAndHandAndPoint(String name, List<Card> hand, int point) {
        resetMessage();
        String printableHand = hand.stream()
            .map(OutputView::formatCard)
            .collect(Collectors.joining(DIVIDE_DELIMITER));
        message.append(name).append(CARD).append(COLON).append(printableHand);
        message.append(RESULT + COLON).append(point);
        System.out.println(message);
    }

    public static void printFinalBenefits(int totalBetAmount, List<String> playerNames, List<Integer> playerBenefits) {
        System.out.println(System.lineSeparator() + FINAL_RESULT);
        int dealerBenefit = totalBetAmount;
        for (Integer playerBenefit : playerBenefits) {
            dealerBenefit -= playerBenefit;
        }
        System.out.println(DEALER + COLON + dealerBenefit);
        for (int i = 0; i < playerNames.size(); i++) {
            System.out.println(playerNames.get(i) + COLON + playerBenefits.get(i));
        }
    }
}
