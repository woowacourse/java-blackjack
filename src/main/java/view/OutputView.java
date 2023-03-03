package view;

import domain.model.Card;
import domain.model.Dealer;
import domain.model.Participant;
import domain.model.Result;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class OutputView {

    private static final String DELIMITER = ", ";
    private static final String NEW_LINE = "\n";
    private static final String PRINT_CARDS_MESSAGE = NEW_LINE + "딜러와 %s에게 2장을 나누었습니다.";
    private static final String COLON = ": ";
    private static final String DEALER_RECEIVE_NOTICE = NEW_LINE + "딜러는 16이하라 한장의 카드를 더 받았습니다." + NEW_LINE;
    private static final String CARD_STATE_RESULT_SIGN = " - 결과: ";
    private static final String VICTORY = "승";
    private static final String DRAW = "무";
    private static final String DEFEAT = "패";
    private static final String CARD = " 카드";
    private static final String RESULT_MESSAGE = NEW_LINE + "## 최종 승패";

    public static void printInitialCards(final Dealer dealer, final List<Participant> participants) {
        printCardsMessage(participants);
        printFirstCard(dealer);
        for (Participant participant : participants) {
            printCard(participant);
        }
        System.out.println();
    }

    private static void printCardsMessage(final List<Participant> participants) {
        final StringJoiner stringJoiner = new StringJoiner(DELIMITER);
        participants.stream()
            .map(Participant::getName)
            .forEach(stringJoiner::add);
        System.out.printf(PRINT_CARDS_MESSAGE, stringJoiner);
        System.out.println();
    }

    private static void printFirstCard(final Dealer dealer) {
        final Card card = dealer.getCards().getCards()
            .stream()
            .findFirst()
            .orElseThrow(IllegalArgumentException::new);
        System.out.println(dealer.getName() + COLON + card);
    }

    public static void printCard(final Participant participant) {
        final String stringifyCard = stringifyCard(participant);
        System.out.println(participant.getName() + COLON + stringifyCard);
    }

    private static String stringifyCard(final Participant participant) {
        final StringJoiner stringJoiner = new StringJoiner(DELIMITER);
        participant.getCards().getCards()
            .stream()
            .map(Card::toString)
            .forEach(stringJoiner::add);
        return stringJoiner.toString();
    }

    public static void printDealerReceiveNotice() {
        System.out.println(DEALER_RECEIVE_NOTICE);
    }

    public static void printTotalCardState(final Dealer dealer, final List<Participant> participants) {
        printParticipantCardState(dealer);
        for (Participant participant : participants) {
            printParticipantCardState(participant);
        }
    }

    private static void printParticipantCardState(final Participant participant) {
        System.out.println(participant.getName() + CARD + COLON + stringifyCard(participant) + CARD_STATE_RESULT_SIGN
            + participant.getScore().getValue());
    }

    public static void printDealerResult(final Result dealerResult, final Dealer dealer) {
        System.out.println(RESULT_MESSAGE);
        System.out.println(dealer.getName() + COLON + stringifyResult(dealerResult));
    }

    public static void printResult(final Map<Participant, Result> participantResults) {
        for (Participant participant : participantResults.keySet()) {
            System.out.println(participant.getName() + COLON + stringifyResult(participantResults.get(participant)));
        }
    }

    private static String stringifyResult(final Result result) {
        final Map<String, Integer> resultHistory = new LinkedHashMap<>();
        resultHistory.put(VICTORY, result.getVictory());
        resultHistory.put(DRAW, result.getDraw());
        resultHistory.put(DEFEAT, result.getDefeat());
        if (result.getVictory() + result.getDraw() + result.getDefeat() == 1) {
            return stringifyOneResultHistory(resultHistory);
        }
        return stringifyResultHistory(resultHistory);
    }

    private static String stringifyResultHistory(final Map<String, Integer> resultHistory) {
        final StringJoiner sj = new StringJoiner(" ");
        resultHistory.keySet().stream()
            .filter(result -> resultHistory.get(result) != 0)
            .forEach(result -> sj.add(resultHistory.get(result) + result));
        return sj.toString();
    }

    private static String stringifyOneResultHistory(final Map<String, Integer> resultHistory) {
        return resultHistory.keySet().stream()
            .filter(result -> resultHistory.get(result) != 0)
            .findAny()
            .orElseThrow(IllegalArgumentException::new);
    }
}
