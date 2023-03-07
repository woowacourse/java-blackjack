package blackjack.view;

import blackjack.dto.ParticipantDto;
import blackjack.dto.ResultDto;

import java.util.List;
import java.util.stream.Collectors;

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

    public void printNameAndHand(ParticipantDto participant) {
        String name = participant.getName();
        String hand = String.join(DELIMITER_COMMA, participant.getCards());
        System.out.println(name + DELIMITER_COLON + hand);
    }

    public void printDealerHitMessage(int hitCount) {
        System.out.println();
        for (int i = 0; i < hitCount; i++) {
            System.out.println(DEALER_HIT_MESSAGE);
        }
        System.out.println();
    }

    public void printScoreResult(ParticipantDto participant, int score, boolean isBlackjack) {
        String name = participant.getName();
        String hand = String.join(DELIMITER_COMMA, participant.getCards());
        String result = Integer.toString(score);
        if (isBlackjack) {
            result += BLACKJACK_MARK;
        }
        System.out.printf(SCORE_RESULT_FORMAT, name, hand, result);
    }

    public void printWinningResultMessage() {
        System.out.println();
        System.out.println(WINNING_RESULT_MESSAGE);
    }

    public void printDealerWinningResult(long win, long tie, long lose) {
        System.out.printf(DEALER_WINNING_RESULT_FORMAT, win, tie, lose);
    }

    public void printPlayerWinningResult(ResultDto result) {
        System.out.println(result.getName() + DELIMITER_COLON + result.getResult());
    }
}
