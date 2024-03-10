package view;

import controller.dto.HandStatus;
import controller.dto.InitialCardStatus;
import controller.dto.JudgeResult;
import controller.dto.ParticipantOutcome;
import domain.card.Card;
import domain.constants.Outcome;
import domain.participant.Hand;
import java.util.List;

public class OutputView {
    private static final String INITIAL_HAND_STATUS_MESSAGE = "%s에게 %d장을 나누었습니다.";
    private static final String NAME_SEPARATOR = ",";
    private static final String HAND_STATUS_MESSAGE = "%s카드: %s";
    private static final String DEALER_CARD_SAVED_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String RESULT_HAND_STATUS_MESSAGE = HAND_STATUS_MESSAGE + " - 결과 : %d";
    private static final String CARD_STATUS_MESSAGE = "%d%s";
    private static final String CARD_SEPARATOR = ",";

    public void printInitialHandStatus(final InitialCardStatus initialCardStatus) {
        List<String> names = generateParticipantNames(initialCardStatus);
        System.out.printf(
                INITIAL_HAND_STATUS_MESSAGE,
                String.join(NAME_SEPARATOR, names),
                initialCardStatus.initialCardSize()
        );

        for (HandStatus handStatus : initialCardStatus.statuses()) {
            printHandStatus(handStatus);
        }
    }

    private List<String> generateParticipantNames(final InitialCardStatus initialCardStatus) {
        return initialCardStatus.statuses().stream()
                .map(HandStatus::name)
                .toList();
    }

    public void printHandStatus(final HandStatus handStatus) {
        System.out.printf(
                HAND_STATUS_MESSAGE,
                handStatus.name(),
                generateCardsStatusMessage(handStatus.hand())
        );
    }

    public void printDealerCardSavedMessage() {
        System.out.println(DEALER_CARD_SAVED_MESSAGE);
    }

    public void printResultHandStatus(final List<HandStatus> handStatuses) {
        for (HandStatus handStatus : handStatuses) {
            System.out.println(String.format(
                    RESULT_HAND_STATUS_MESSAGE,
                    handStatus.name(),
                    generateCardsStatusMessage(handStatus.hand()),
                    handStatus.hand().calculateResultScore()
            ));
        }
    }

    private String generateCardsStatusMessage(final Hand hand) {
        List<String> cardsStatus = generateCardsStatus(hand.getCards());
        return String.join(CARD_SEPARATOR, cardsStatus);
    }

    private List<String> generateCardsStatus(final List<Card> cards) {
        return cards.stream()
                .map(card -> String.format(
                        CARD_STATUS_MESSAGE,
                        card.getScore(),
                        card.getShape())
                )
                .toList();
    }

    public void printGameResult(final JudgeResult results) {
        System.out.println();
        System.out.println("## 최종 승패");
        List<ParticipantOutcome> participantOutcomes = results.results();
        int loseCount = (int) participantOutcomes.stream()
                .filter(participantOutcome -> participantOutcome.outcome().equals(Outcome.LOSE))
                .count();
        System.out.println("딜러: " + (participantOutcomes.size() - loseCount) + "승 " + loseCount + "패");
        for (ParticipantOutcome participantOutcome : participantOutcomes) {
            System.out.println(
                    participantOutcome.name() + ": " + checkIsWin(participantOutcome.outcome().equals(Outcome.WIN)));
        }
    }

    private String checkIsWin(final boolean isWin) {
        if (isWin) {
            return "승";
        }
        return "패";
    }
}
