package view;

import domain.card.Card;
import domain.result.Result;
import domain.ExceptionCode;

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

    public static void printPlayersName(List<String> participantNames) {
        printEmptyLine();
        String participants = String.join(DELIMITER, participantNames);
        System.out.printf(INIT_FINISHIED_MESSAGE, participants);
    }

    public static void printParticipantCard(String name, List<String> participantsHand) {
        String cards = String.join(DELIMITER, participantsHand);
        System.out.printf((PARTICIPANT_CARD_FORMAT), name, cards);
    }

    public static void printDealerPickCardMessage() {
        System.out.println(DEALER_HIT_MESSAGE);
        printEmptyLine();
    }

    public static void printParticipantHandValue(String participantName, int handValue, List<Card> participantCards, boolean isBust) {
        List<String> cardNames = new ArrayList<>();
        summariseParticipantHand(participantCards, cardNames);
        String cards = String.join(DELIMITER, cardNames);
        if (isBust) {
            System.out.printf((PARTICIPANT_HAND_SUM), participantName, cards, "버스트");
            return;
        }
        System.out.printf((PARTICIPANT_HAND_SUM), participantName, cards, handValue);
    }

    private static void summariseParticipantHand(final List<Card> participantCards, final List<String> cardNames) {
        for (Card card : participantCards) {
            final String rankMessage = RankMessage.getRankMessage(card.getRank());
            final String suitMessage = SuitMessage.getSuitMessage(card.getSuit());
            cardNames.add(rankMessage + suitMessage);
        }
    }

    public static void printEmptyLine() {
        System.out.println();
    }

    public static void printDealerResult(Map<Result, Integer> dealerResult) {
        System.out.printf((DEALER_RESULT_FORMAT),
                dealerResult.getOrDefault(Result.WIN,0),
                dealerResult.getOrDefault(Result.TIE,0),
                dealerResult.getOrDefault(Result.LOSE,0));
    }

    public static void printPlayerResult(String name, Result result) {
        final String resultMessage = exchangeResultMessage(result);
        System.out.printf((RESULT_FORMAT), name, resultMessage);
    }

    private static String exchangeResultMessage(final Result result) {
        if (result.equals(Result.WIN)) {
            return WIN_MESSAGE;
        }
        if (result.equals(Result.LOSE)) {
            return LOSE_MESSAGE;
        }
        return TIE_MESSAGE;
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
}
