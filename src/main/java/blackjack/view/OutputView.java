package blackjack.view;

import java.util.List;
import java.util.Map;

public class OutputView {

    private static final String INIT_END_MSG = "에게 2장을 나누었습니다.";
    private static final String INIT_START_MSG = "딜러와 ";
    private static final String CARD_MSG = "카드: ";
    private static final String DEALER_MSG = "딜러: ";
    private static final String DEALER_HIT_MSG = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String RESULT_MSG = " - 결과: ";
    private static final String FINAL_START_MSG = "## 최종 승패";
    private static final String RESULT_DELIMITER = ": ";
    private static final String CARD_DELIMITER = ", ";
    private static final String BLANK = " ";
    private static final String EMPTY = "";
    private static final int ZERO = 0;

    public void printStartMsg(List<String> names) {
        System.out.println();
        int lastIndex = names.size() - 1;
        System.out.print(INIT_START_MSG);
        for (int i = 0; i < lastIndex; i++) {
            System.out.print(names.get(i) + CARD_DELIMITER);
        }
        System.out.println(names.get(lastIndex) + INIT_END_MSG);
    }

    public void printPlayerCards(String name, List<String> cards, String end) {
        int lastIndex = cards.size() - 1;
        System.out.print(name + CARD_MSG);
        for (int i = 0; i < lastIndex; i++) {
            System.out.print(cards.get(i) + CARD_DELIMITER);
        }
        System.out.print(cards.get(lastIndex) + end);
    }

    public void printDealerCards(List<String> cards, String end) {
        int lastIndex = cards.size() - 1;
        System.out.print(DEALER_MSG);
        for (int i = 0; i < lastIndex; i++) {
            System.out.print(cards.get(i) + CARD_DELIMITER);
        }
        System.out.print(cards.get(lastIndex) + end);
    }

    public void printDealerHit() {
        System.out.println();
        System.out.println(DEALER_HIT_MSG);
    }

    public void printDealerResult(List<String> cardNames, int calculateScore) {
        System.out.println();
        printDealerCards(cardNames, EMPTY);
        System.out.println(RESULT_MSG + calculateScore);
    }

    public void printPlayerResult(String showName, List<String> cardNames, int calculateScore) {
        printPlayerCards(showName, cardNames, EMPTY);
        System.out.println(RESULT_MSG + calculateScore);
    }

    public void printEndMsg() {
        System.out.println();
        System.out.println(FINAL_START_MSG);
    }

    public void printPlayerWinningResult(Map<String, String> playerResult) {
        for (String playerName : playerResult.keySet()) {
            System.out.println(playerName + RESULT_DELIMITER + playerResult.get(playerName));
        }
    }

    public void printDealerWinningResult(Map<String, Integer> dealerResult) {
        StringBuilder dealerResultMsg = new StringBuilder(DEALER_MSG);
        for (String result : dealerResult.keySet()) {
            if (dealerResult.get(result) != ZERO) {
                dealerResultMsg.append(dealerResult.get(result)).append(result).append(BLANK);
            }
        }
        System.out.println(dealerResultMsg);
    }
}
