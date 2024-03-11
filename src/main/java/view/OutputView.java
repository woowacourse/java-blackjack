package view;

import static domain.constants.Outcome.WIN;

import controller.dto.HandStatus;
import controller.dto.InitialCardStatus;
import controller.dto.JudgeResult;
import controller.dto.ParticipantOutcome;
import domain.card.Card;
import domain.constants.Outcome;
import domain.participant.Dealer;
import domain.participant.Hand;
import java.util.List;

public class OutputView {
    private static final String INITIAL_HAND_STATUS_FORMAT = "%s에게 %d장을 나누었습니다.";
    private static final String NAME_SEPARATOR = ",";
    private static final String HAND_STATUS_FORMAT = "%s카드: %s";
    private static final String DEALER_CARD_SAVED_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String RESULT_HAND_STATUS_MESSAGE = HAND_STATUS_FORMAT + " - 결과 : %d";
    private static final String CARD_STATUS_MESSAGE = "%d%s";
    private static final String CARD_SEPARATOR = ",";
    private static final String JUDGE_RESULT_MESSAGE = "## 최종 승패";
    private static final String WIN_MESSAGE = "승";
    private static final String LOSE_MESSAGE = "패";
    private static final String JUDGE_DEALER_RESULT_FORMAT = "%s: %d" + WIN_MESSAGE + "%d" + LOSE_MESSAGE;
    private static final String JUDGE_PLAYER_RESULT_FORMAT = "%s: %s";


    public void printInitialHandStatus(final InitialCardStatus initialCardStatus) {
        List<String> names = generateParticipantNames(initialCardStatus);
        System.out.printf(
                INITIAL_HAND_STATUS_FORMAT,
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
                HAND_STATUS_FORMAT,
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

    public void printJudgeResult(final JudgeResult judgeResult) {
        System.out.println(JUDGE_RESULT_MESSAGE);

        int winnersCount = judgeResult.countWinner();
        int losersCount = judgeResult.results().size() - winnersCount;
        System.out.printf(JUDGE_DEALER_RESULT_FORMAT, Dealer.DEALER_NAME, winnersCount, losersCount);

        for (ParticipantOutcome result : judgeResult.results()) {
            System.out.printf(
                    JUDGE_PLAYER_RESULT_FORMAT,
                    result.name(),
                    findJudgeResultMessage(result.outcome())
            );
        }
    }

    private String findJudgeResultMessage(final Outcome outcome) {
        if (WIN.equals(outcome)) {
            return WIN_MESSAGE;
        }
        return LOSE_MESSAGE;
    }
}
