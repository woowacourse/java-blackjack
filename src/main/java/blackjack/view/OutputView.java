package blackjack.view;

import java.util.List;
import java.util.Map;

public class OutputView {

    private static final String DELIMITER_COMMA = ", ";
    private static final String DELIMITER_COLON = ": ";
    private static final String DISTRIBUTION_MESSAGE_FORMAT = "딜러와 %s에게 2장을 나누었습니다.\n";
    private static final String DEALER_HIT_MESSAGE = "딜러가 16이하라 한장의 카드를 더 받았습니다.";
    private static final String BLACKJACK_MARK = " (블랙잭)";
    private static final String SCORE_RESULT_FORMAT = "%s: %s - 결과: %s\n";
    private static final String WINNING_RESULT_MESSAGE = "## 최종 승패";
    private static final String DEALER_WINNING_RESULT_FORMAT = "딜러: %d승 %d무 %d패\n";

    private static OutputView instance;

    private OutputView() {
    }

    public static OutputView getInstance() {
        if (instance == null) {
            instance = new OutputView();
            return instance;
        }
        return instance;
    }

    public void printDistributionMessage(List<String> players) {
        String names = String.join(DELIMITER_COMMA, players);
        System.out.printf(DISTRIBUTION_MESSAGE_FORMAT, names);
    }

    public void printNameAndHand(Map<String, List<String>> namesAndHands) {
        for (Map.Entry<String, List<String>> entry : namesAndHands.entrySet()) {
            String name = entry.getKey();
            String hand = String.join(DELIMITER_COMMA, entry.getValue());
            System.out.println(name + DELIMITER_COLON + hand);
        }
    }

    public void printDealerHitMessage(int hitCount) {
        System.out.println();
        for(int i = 0; i < hitCount; i++){
            System.out.println(DEALER_HIT_MESSAGE);
        }
        System.out.println();
    }

    public void printScoreResult(Map<String, List<String>> namesAndHands, int score, boolean isBlackjack) {
        for (Map.Entry<String, List<String>> entry : namesAndHands.entrySet()) {
            String name = entry.getKey();
            String cards = String.join(DELIMITER_COMMA, entry.getValue());
            String result = Integer.toString(score);
            if(isBlackjack){
                result += BLACKJACK_MARK;
            }
            System.out.printf(SCORE_RESULT_FORMAT, name, cards, result);
        }
    }

    public void printWinningResultMessage() {
        System.out.println();
        System.out.println(WINNING_RESULT_MESSAGE);
    }

    public void printDealerWinningResult(long win, long tie, long lose) {
        System.out.printf(DEALER_WINNING_RESULT_FORMAT, win, tie, lose);
    }

    public void printPlayersWinningResult(Map<String, String> playerResult) {
        for (Map.Entry<String, String> entry : playerResult.entrySet()) {
            System.out.println(entry.getKey() + DELIMITER_COLON + entry.getValue());
        }
    }
}
