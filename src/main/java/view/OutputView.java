package view;

import domain.Card;
import domain.Result;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OutputView {
    private static final String PARTICIPANT_CARD_FORMAT = "%s : %s\n";
    private static final String PARTICIPANT_HAND_SUM = "%s 카드: %s - 결과: %s\n";
    private static final String INIT_FINISHIED_MESSAGE = "딜러와 %s에게 2장을 나누었습니다.\n";
    private static final String DEALER_HIT_MESSAGE = "딜러는 16이하라 카드를 더 받았습니다.";
    private static final String DEALER_RESULT_FORMAT = "딜러: %s승 %s무 %s패\n";
    private static final String RESULT_FORMAT = "%s: %s";

    private static final String RESULT_TAG = "## 최종 승패";
    private static final String WIN_MESSAGE = "승\n";
    private static final String TIE_MESSAGE = "무\n";
    private static final String LOSE_MESSAGE = "패\n";

    private static final String DELIMITER = ", ";

    public void printPlayersName(List<String> participantNames) {
        printEmptyLine();
        String participants = String.join(DELIMITER, participantNames);
        System.out.printf(INIT_FINISHIED_MESSAGE, participants);
    }

    public void printParticipantCard(String name, List<String> participantsHand) {
        String cards = String.join(DELIMITER, participantsHand);
        System.out.printf((PARTICIPANT_CARD_FORMAT), name, cards);
    }

    public void printDealerPickCardMessage() {
        System.out.println(DEALER_HIT_MESSAGE);
        printEmptyLine();
    }

    public void printParticipantHandValue(String participantName, int handValue, List<Card> participantCards, boolean isBust) {
        List<String> cardNames = new ArrayList<>();
        summariseParticipantHand(participantCards, cardNames);
        String cards = String.join(DELIMITER, cardNames);
        if (isBust) {
            System.out.printf((PARTICIPANT_HAND_SUM), participantName, cards, "버스트");
            return;
        }
        System.out.printf((PARTICIPANT_HAND_SUM), participantName, cards, handValue);
    }

    private void summariseParticipantHand(final List<Card> participantCards, final List<String> cardNames) {
        for (Card participantCard : participantCards) {
            cardNames.add(participantCard.getRank().getName() + participantCard.getSuit().getShape());
        }
    }

    public void printEmptyLine() {
        System.out.println();
    }

    public void printDealerResult(Map<Result, Integer> dealerResult) {
        System.out.printf((DEALER_RESULT_FORMAT),
                dealerResult.getOrDefault(Result.WIN,0),
                dealerResult.getOrDefault(Result.TIE,0),
                dealerResult.getOrDefault(Result.LOSE,0));
    }

    public void printPlayerResult(String name, Result result) {
        final String resultMessage = exchangeResultMessage(result);
        System.out.printf((RESULT_FORMAT), name, resultMessage);
    }

    private String exchangeResultMessage(final Result result) {
        if (result.equals(Result.WIN)) {
            return WIN_MESSAGE;
        }
        if (result.equals(Result.LOSE)) {
            return LOSE_MESSAGE;
        }
        return TIE_MESSAGE;
    }

    public void printResultInfo() {
        printEmptyLine();
        System.out.println(RESULT_TAG);
    }

    public void printExceptionMessage(IllegalArgumentException e) {
        System.out.println(e.getMessage());
    }
}
