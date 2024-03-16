package view;

import domain.bet.BetResult;
import domain.card.Card;
import domain.participant.Name;
import java.util.List;
import java.util.Map;

public class OutputView {

    private static final OutputFormat outputFormat = new OutputFormat();

    private OutputView() {

    }

    public static void printInitBlackJack(List<Name> names) {
        System.out.println();
        System.out.printf(outputFormat.formatPlayersNames(names));
        System.out.println();
    }

    public static void printDealerInitHands(List<Card> cards) {
        System.out.printf("딜러: %s", outputFormat.formatCards(cards));
        System.out.println();
    }

    public static void printPlayerHands(Name name, List<Card> cards) {
        System.out.printf(outputFormat.formatHands(name, cards));
        System.out.println();
    }

    public static void printDealerHit(int count) {
        System.out.println();
        for (int i = 0; i < count; i++) {
            System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
            System.out.println();
        }
    }

    public static void printParticipantScore(Name name, List<Card> cards, int score) {
        System.out.printf(outputFormat.formatParticipantScore(name, cards, score));
        System.out.println();
    }

    public static void printBetResultMessage() {
        System.out.println();
        System.out.println("## 최종 수익");
    }

    public static void printDealerBetResult(double dealerProfit) {
        System.out.printf("딜러: %s", (int) dealerProfit);
        System.out.println();
    }

    public static void printPlayersBetResult(BetResult betResult) {
        Map<Name, Double> betAmountByParticipant = betResult.getBetAmountByParticipant();
        for (Map.Entry<Name, Double> entry : betAmountByParticipant.entrySet()) {
            System.out.println(outputFormat.formatBetResult(entry));
        }
    }

    public static void printBlank() {
        System.out.println();
    }
}
