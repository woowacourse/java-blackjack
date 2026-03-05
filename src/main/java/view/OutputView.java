package view;

import domain.Participant;
import domain.Participants;
import domain.card.Card;
import java.util.List;

public class OutputView {


    private static final String DELIMITER = ", ";
    private static final String NEW_LINE = System.lineSeparator();

    public static void showIntroMessage(Participants participants) {
        System.out.println(NEW_LINE + "딜러와 "
                + String.join(DELIMITER, String.join(DELIMITER, participants.getPlayerNames())
                + "에게 2장을 나누었습니다."));
    }

    public static void showCardName(Participant participant) {
        List<String> playerCardNames = createCardNames(participant);
        System.out.println(getCardNames(participant, playerCardNames));
    }

    private static String getCardNames(Participant participant, List<String> playerCardNames) {
        return participant.getName() + "카드: " + String.join(DELIMITER, playerCardNames);
    }

    private static List<String> createCardNames(Participant participant) {
        return participant.getCards().stream()
                .map(Card::getCardName)
                .toList();
    }

    public static void showDealerMessage() {
        System.out.println(NEW_LINE + "딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void showResult(Participant dealer, Participants participants) {
        System.out.println();
        List<String> dealerCardNames = createCardNames(dealer);
        System.out.println(getCardNames(dealer, dealerCardNames) + " - 결과: " + dealer.getScore());

        for (Participant participant : participants.getPlayers()) {
            List<String> playerCardNames = createCardNames(participant);
            System.out.println(getCardNames(participant, playerCardNames) + " - 결과: " + participant.getScore());
        }
    }
}
