package view;

import java.util.List;
import java.util.Map;

import domain.Result;

public class OutputView {
    private static final String PRINT_PARTICIPANT_CARD_FORMAT = "%s : %s\n";
    private static final String PRINT_PARTICIPANT_HAND_SUM = "%s 카드: %s - 결과: %s\n";

    public void printInitializingFinishMessage(List<String> participantNames) {
        printEmptyLine();
        String participants = String.join(", ", participantNames);
        System.out.printf("딜러와 %s에게 2장을 나누었습니다.\n", participants);
    }

    public void printParticipantCard(String name, List<String> participantsHand) {
        String cards = String.join(", ", participantsHand);
        System.out.printf((PRINT_PARTICIPANT_CARD_FORMAT), name, cards);
    }

    public void printDealerPickCardMessage() {
        System.out.println("딜러는 16이하라 카드를 더 받았습니다.");
        printEmptyLine();
    }

    public void printParticipantHandValue(String participantName, List<String> participantCards, String handValue) {
        String cards = String.join(", ", participantCards);
        System.out.printf((PRINT_PARTICIPANT_HAND_SUM), participantName, cards, handValue);
    }

    public void printEmptyLine() {
        System.out.println();
    }

    public void printDealerResult(Map<Result, Integer> dealerResult) {
        StringBuilder result = new StringBuilder();
        result.append("딜러: ");
        if (dealerResult.getOrDefault(Result.WIN, 0) > 0) {
            result.append(dealerResult.get(Result.WIN)).append(Result.WIN.getResult());
        }
        if (dealerResult.getOrDefault(Result.TIE, 0) > 0) {
            result.append(dealerResult.get(Result.TIE)).append(Result.TIE.getResult());
        }
        if (dealerResult.getOrDefault(Result.LOSE, 0) > 0) {
            result.append(dealerResult.get(Result.LOSE)).append(Result.LOSE.getResult());
        }

        System.out.println(result);
    }

    public void printPlayerResult(String name, Result result) {
        System.out.println(name + ": " + result.getResult());
    }

    public void printResultInfo() {
        printEmptyLine();
        System.out.println("## 최종 승패");
    }
}
