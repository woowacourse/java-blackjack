package view;

import java.util.List;

public class OutputView {
    private static final String PRINT_PARTICIPANT_CARD_FORMAT = "%s : %s\n";
    private static final String PRINT_PARTICIPANT_HAND_SUM = "%s 카드: %s - 결과: %d\n";

    public void printParticipantCard(String participantName, List<String> participantCards) {
        String cards = String.join(",", participantCards);
        System.out.printf((PRINT_PARTICIPANT_CARD_FORMAT), participantName, cards);
    }

    public void printDealerPickCardMessage() {
        System.out.println("딜러는 16이하라 카드를 더 받았습니다.");
    }

    public void printParticipantHandValue(String participantName, List<String> participantCards, int handValue) {
        String cards = String.join(",", participantCards);
        System.out.printf((PRINT_PARTICIPANT_HAND_SUM), participantName, cards, handValue);
    }
}
