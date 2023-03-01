package view;

import java.util.List;
import java.util.Map;

public class OutputView {
    private static final String PRINT_PARTICIPANT_CARD_FORMAT = "%s : %s\n";
    private static final String PRINT_PARTICIPANT_HAND_SUM = "%s 카드: %s - 결과: %d\n";

    public void printInitializingFinishMessage(List<String> participantNames) {
        printEmptyLine();
        String participants = String.join(", ", participantNames);
        System.out.printf("딜러%s에게 2장을 나누었습니다.\n", participants);
    }

    public void printParticipantCard(Map<String, List<String>> participantsHands) {
        for (Map.Entry<String, List<String>> participantHand : participantsHands.entrySet()) {
            String cards = String.join(", ", participantHand.getValue());
            System.out.printf((PRINT_PARTICIPANT_CARD_FORMAT), participantHand.getKey(), cards);
        }
    }

    public void printDealerPickCardMessage() {
        System.out.println("딜러는 16이하라 카드를 더 받았습니다.");
    }

    public void printParticipantHandValue(String participantName, List<String> participantCards, int handValue) {
        String cards = String.join(", ", participantCards);
        System.out.printf((PRINT_PARTICIPANT_HAND_SUM), participantName, cards, handValue);
    }

    public void printEmptyLine() {
        System.out.println();
    }
}
