package blackjack.view;

import blackjack.dto.FinishedParticipantDto;
import blackjack.dto.ParticipantDto;
import blackjack.dto.ProfitResultDto;

import java.util.List;

public class OutputView {

    private static final String DELIMITER_COMMA = ", ";
    private static final String DELIMITER_COLON = ": ";
    private static final String DISTRIBUTION_MESSAGE_FORMAT = "딜러와 %s에게 2장을 나누었습니다.\n";
    private static final String DEALER_HIT_MESSAGE = "딜러가 16이하라 한장의 카드를 더 받았습니다.";
    private static final String BLACKJACK_MARK = " (블랙잭)";
    private static final String SCORE_RESULT_FORMAT = "%s: %s - 결과: %s\n";
    private static final String PROFIT_RESULT_MESSAGE = "## 최종 수익";

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

    public void printAllCardStatus(List<ParticipantDto> participants){
        participants.forEach(this::printSingleCardStatus);
    }

    public void printSingleCardStatus(ParticipantDto participant) {
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

    public void printAllFinalCardStatus(List<FinishedParticipantDto> participants){
        participants.forEach(this::printSingleFinalCardStatus);
    }

    private void printSingleFinalCardStatus(FinishedParticipantDto participant) {
        String name = participant.getName();
        String hand = String.join(DELIMITER_COMMA, participant.getCards());
        String result = participant.getScore();
        if (participant.isBlackjack()) {
            result += BLACKJACK_MARK;
        }
        System.out.printf(SCORE_RESULT_FORMAT, name, hand, result);
    }

    public void printProfitResultMessage() {
        System.out.println();
        System.out.println(PROFIT_RESULT_MESSAGE);
    }

    public void printAllProfitResult(List<ProfitResultDto> results) {
        for(ProfitResultDto result : results){
            System.out.printf(result.getName() + DELIMITER_COLON + result.getProfit());
            System.out.println();
        }
    }
}
