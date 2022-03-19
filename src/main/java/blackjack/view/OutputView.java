package blackjack.view;

import blackjack.model.participant.Participant;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private static final String PARTICIPANT_START_RESULT_FORMAT = "%s에게 2장의 카드를 나누었습니다.%n";
    private static final String PARTICIPANT_RESULT_FORMAT = "%s: %s";
    private static final String PARTICIPANT_SCORE_RESULT_FORMAT = "%s: %s - 결과: %d";

    private static final String JOINER = ", ";

    public static void printStartResult(List<Participant> participants) {
        System.out.println();
        System.out.printf(PARTICIPANT_START_RESULT_FORMAT, joinParticipantNames(participants));
        participants.forEach(OutputView::printParticipantCardsResult);
    }

    private static String joinParticipantNames(List<Participant> participants) {
        return participants.stream()
                .map(Participant::getName)
                .collect(Collectors.joining(JOINER));
    }

    public static void printParticipantCardsResult(Participant participant) {
        System.out.printf(PARTICIPANT_RESULT_FORMAT, participant.getName(),
                String.join(JOINER, participant.getCards()));
        System.out.println();
    }

    public static void printParticipantsScoreResult(List<Participant> participants) {
        System.out.println();
        participants.forEach(OutputView::printParticipantScoreResult);
    }

    private static void printParticipantScoreResult(Participant participant) {
        System.out.printf(PARTICIPANT_SCORE_RESULT_FORMAT, participant.getName(),
                String.join(JOINER, participant.getCards()), participant.getScore());
        System.out.println();
    }

    public static void printParticipantsBettingResult(Map<String, Double> bettingResult) {
        System.out.println();
        System.out.println("## 최종 수익");
        bettingResult.forEach(OutputView::printParticipantBettingResult);
    }

    private static void printParticipantBettingResult(String name, Double money) {
        System.out.printf(PARTICIPANT_RESULT_FORMAT, name, money.intValue());
        System.out.println();
    }
}
