package view;

import controller.dto.response.InitialCardStatus;
import controller.dto.response.ParticipantHandStatus;
import controller.dto.response.ParticipantProfitResponse;
import domain.card.Card;
import domain.participant.Hand;
import java.util.List;

public class OutputView {
    private static final String INITIAL_HAND_STATUS_FORMAT = "%n%s에게 %d장을 나누었습니다.%n";
    private static final String NAME_SEPARATOR = ", ";
    private static final String HAND_STATUS_FORMAT = "%s카드: %s%n";
    private static final String DEALER_CARD_SAVED_MESSAGE = "%n딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String RESULT_HAND_STATUS_MESSAGE = "%s카드: %s - 결과 : %d";
    private static final String CARD_STATUS_MESSAGE = "%s%s";
    private static final String CARD_SEPARATOR = ", ";
    private static final String PARTICIPANT_PROFIT_RESPONSE_MESSAGE = "%n## 최종 수익";
    private static final String PARTICIPANT_PROFIT_RESULT_FORMAT = "%n%s: %d";

    public void printInitialHandStatus(final InitialCardStatus initialCardStatus) {
        List<String> names = generateParticipantNames(initialCardStatus);
        System.out.printf(
                INITIAL_HAND_STATUS_FORMAT,
                String.join(NAME_SEPARATOR, names),
                initialCardStatus.initialCardSize()
        );

        for (ParticipantHandStatus participantHandStatus : initialCardStatus.statuses()) {
            printHandStatus(participantHandStatus);
        }
        System.out.println();
    }

    private List<String> generateParticipantNames(final InitialCardStatus initialCardStatus) {
        return initialCardStatus.statuses().stream()
                .map(ParticipantHandStatus::name)
                .toList();
    }

    public void printHandStatus(final ParticipantHandStatus participantHandStatus) {
        System.out.printf(
                HAND_STATUS_FORMAT,
                participantHandStatus.name(),
                generateCardsStatusMessage(participantHandStatus.hand())
        );
    }

    public void printDealerCardSavedMessage() {
        System.out.printf(DEALER_CARD_SAVED_MESSAGE);
        System.out.println();
    }

    public void printResultHandStatus(final List<ParticipantHandStatus> participantHandStatuses) {
        System.out.println();
        for (ParticipantHandStatus participantHandStatus : participantHandStatuses) {
            System.out.println(String.format(
                    RESULT_HAND_STATUS_MESSAGE,
                    participantHandStatus.name(),
                    generateCardsStatusMessage(participantHandStatus.hand()),
                    participantHandStatus.hand().calculateResultScore()
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
                        card.getName(),
                        card.getShape())
                )
                .toList();
    }

    public void printParticipantProfitResponse(final List<ParticipantProfitResponse> responses) {
        System.out.printf(PARTICIPANT_PROFIT_RESPONSE_MESSAGE);

        for (ParticipantProfitResponse response : responses) {
            System.out.printf(
                    PARTICIPANT_PROFIT_RESULT_FORMAT,
                    response.name(),
                    response.profit()
            );
        }
        System.out.println();
    }
}
