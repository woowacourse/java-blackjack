package view;

import controller.dto.HandStatus;
import controller.dto.JudgeResult;
import controller.dto.ParticipantOutcome;
import domain.Card;
import domain.Hand;
import domain.constants.Outcome;
import java.util.List;

public class OutputView {

    private static final String RESULT_HAND_STATUS_MESSAGE = "%s카드: %s - 결과 : %d";
    private static final String CARD_STATUS_MESSAGE = "%d%s";
    private static final String CARD_SEPARATOR = ",";

    public void printInitialStatus(final List<HandStatus> statuses) {
        System.out.println();
        StringBuilder builder = new StringBuilder("딜러와 ");

        List<String> playerNames = getPlayerNames(statuses);
        builder.append(String.join(", ", playerNames));
        builder.append("에게 2장을 나누었습니다.\n");

        for (HandStatus handStatus : statuses) {
            builder.append(handStatus.getCardInitStatus());
            builder.append("\n");
        }

        System.out.println(builder);
    }

    private List<String> getPlayerNames(final List<HandStatus> statuses) {
        return statuses.subList(1, statuses.size()).stream()
                .map(HandStatus::name)
                .toList();
    }

    public void printCardStatus(final HandStatus status) {
        System.out.println(status.getCardInitStatus());
    }

    public void printDealerPickMessage() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
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
