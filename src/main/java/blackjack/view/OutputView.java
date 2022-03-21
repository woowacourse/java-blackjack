package blackjack.view;

import blackjack.model.betting.BettingResult;
import blackjack.model.card.Card;
import blackjack.model.participant.Participant;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final String PARTICIPANT_NAME_PRINT_FORMAT = "%s";
    private static final String PARTICIPANT_CARD_PRINT_FORMAT = ": %s ";
    private static final String PARTICIPANT_SCORE_PRINT_FORMAT = "- 결과: %d";
    private static final String PARTICIPANT_PROFIT_PRINT_FORMAT = ": %d";

    private static final String JOINER = ", ";

    public static void printStartResult(List<Participant> participants) {
        System.out.printf(PARTICIPANT_NAME_PRINT_FORMAT, joinNames(participants) + "에게 2장의 카드를 나누었습니다.");
        System.out.println();
        participants.forEach(participant -> {
            printNameResult(participant.getName());
            printCardsResult(participant.getCards());
            System.out.println();
        });
    }

    private static String joinNames(List<Participant> participants) {
        return participants.stream()
                .map(Participant::getName)
                .collect(Collectors.joining(JOINER));
    }

    public static void printTurnResult(Participant participant) {
        printNameResult(participant.getName());
        printCardsResult(participant.getCards());
        System.out.println();
    }

    public static void printFinishResult(List<Participant> participants) {
        System.out.println();
        participants.forEach(participant -> {
            printNameResult(participant.getName());
            printCardsResult(participant.getCards());
            printScoreResult(participant.getScore());
            System.out.println();
        });
    }

    public static void printBettingResult(BettingResult bettingResults) {
        System.out.println();
        System.out.println("## 최종 수익");
        bettingResults.getResult().forEach((name, profit) -> {
            printNameResult(name);
            printProfitResult(profit);
            System.out.println();
        });
    }

    private static void printNameResult(String name) {
        System.out.printf(PARTICIPANT_NAME_PRINT_FORMAT, name);
    }

    private static void printCardsResult(List<Card> cards) {
        System.out.printf(PARTICIPANT_CARD_PRINT_FORMAT, joinCards(cards));
    }

    private static String joinCards(List<Card> cards) {
        return cards.stream()
                .map(card -> card.getNumber().getValueForPrint() + card.getSymbol().getValue())
                .collect(Collectors.joining(JOINER));
    }

    private static void printScoreResult(int score) {
        System.out.printf(PARTICIPANT_SCORE_PRINT_FORMAT, score);
    }

    private static void printProfitResult(Double profit) {
        System.out.printf(PARTICIPANT_PROFIT_PRINT_FORMAT, profit.intValue());
    }
}
