package blackjack.view;

import blackjack.model.participant.Participant;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final String PARTICIPANT_START_RESULT_FORMAT = "%n%s에게 2장의 카드를 나누었습니다.%n";
    private static final String PARTICIPANT_CARDS_RESULT_FORMAT = "%n%s: %s";
    private static final String PARTICIPANT_SCORE_RESULT_FORMAT = "%n%s: %s - 결과: %d";

    private static final String JOINNER = ", ";

    public static void printStartResult(List<Participant> participants) {
        System.out.printf(PARTICIPANT_START_RESULT_FORMAT, joinParticipantNames(participants));
        participants.forEach(OutputView::printParticipantCardsResult);
        System.out.println();
    }

    private static String joinParticipantNames(List<Participant> participants) {
        return participants.stream()
                .map(Participant::getName)
                .collect(Collectors.joining(JOINNER));
    }

    public static void printParticipantCardsResult(Participant participant) {
        System.out.printf(PARTICIPANT_CARDS_RESULT_FORMAT, participant.getName(),
                String.join(JOINNER, participant.getCards()));
    }

    public static void printParticipantsScoreResult(List<Participant> participants) {
        System.out.println();
        participants.forEach(OutputView::printParticipantScoreResult);
    }

    private static void printParticipantScoreResult(Participant participant) {
        System.out.printf(PARTICIPANT_SCORE_RESULT_FORMAT, participant.getName(),
                String.join(JOINNER, participant.getCards()), participant.getScore());
    }
}
