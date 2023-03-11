package view;

import domain.ExceptionCode;
import domain.card.Card;
import domain.participant.Participant;
import domain.participant.Participants;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OutputView {
    private static final String PARTICIPANT_CARD_FORMAT = "%s : %s\n";
    private static final String PARTICIPANT_HAND_SUM = "%s 카드: %s - 결과: %s\n";
    private static final String INIT_FINISHIED_MESSAGE = "딜러와 %s에게 2장을 나누었습니다.\n";
    private static final String BUST_MESSAGE = "버스트";
    private static final String DEALER_HIT_MESSAGE = "딜러는 16이하라 카드를 더 받았습니다.";

    private static final String RESULT_TAG = "## 최종 수익";
    private static final String DEALER_PRIZE_FORMAT = "딜러: %d";
    private static final String PLAYER_PRIZE_FORMAT = "%s: %d";

    private static final String DELIMITER = ", ";

    public static void printInitDealFinishingMessage(Participants participants) {
        printEmptyLine();
        String participantsName = String.join(DELIMITER, participants.getPlayersName());
        System.out.printf(INIT_FINISHIED_MESSAGE, participantsName);
    }

    public static void printParticipantCard(String name, List<String> participantsHand) {
        String cards = String.join(DELIMITER, participantsHand);
        System.out.printf((PARTICIPANT_CARD_FORMAT), name, cards);
    }

    public static void printDealerPickCardMessage() {
        System.out.println(DEALER_HIT_MESSAGE);
        printEmptyLine();
    }

    public static void printParticipantHandValue(Participant player, boolean isBust) {
        List<String> cardNames = new ArrayList<>();
        summariseParticipantHand(player.getCards(), cardNames);
        String cards = String.join(DELIMITER, cardNames);
        if (isBust) {
            System.out.printf((PARTICIPANT_HAND_SUM), player.getName(), cards, BUST_MESSAGE);
            return;
        }
        System.out.printf((PARTICIPANT_HAND_SUM), player.getName(), cards, player.getHandValue());
    }

    public static void printEmptyLine() {
        System.out.println();
    }

    public static void printParticipantsPrize(int dealerPrize, Map<Participant, Integer> participantsPrize) {
        System.out.println(String.format(DEALER_PRIZE_FORMAT, dealerPrize));
        for (Map.Entry<Participant, Integer> participantPrize : participantsPrize.entrySet()) {
            System.out.println(String.format(PLAYER_PRIZE_FORMAT, participantPrize.getKey().getName(), participantPrize.getValue()));
        }
    }

    public static void printResultInfo() {
        printEmptyLine();
        System.out.println(RESULT_TAG);
    }

    public static void printExceptionMessage(IllegalArgumentException e) {
        final ExceptionCode exceptionCode = ExceptionCode.getExceptionCodeName(e.getMessage());
        final String exceptionMessage = ExceptionMessage.getExceptionMessage(exceptionCode);

        System.out.println(exceptionMessage);
    }

    private static void summariseParticipantHand(final List<Card> participantCards, final List<String> cardNames) {
        for (Card card : participantCards) {
            final String rankMessage = RankMessage.getRankMessage(card.getRank());
            final String suitMessage = SuitMessage.getSuitMessage(card.getSuit());
            cardNames.add(rankMessage + suitMessage);
        }
    }
}
