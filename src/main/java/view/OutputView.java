package view;

import domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OutputView {
    private static final OutputFormat outputFormat = new OutputFormat();

    private OutputView() {}
    public static void printParticipants(Participants participants) {
        System.out.println();
        System.out.printf(outputFormat.formatParticipantNames(participants));
        System.out.println();
        System.out.println();
    }

    public static void printDealerCard(Dealer dealer) {
        Name name = dealer.getName();
        List<Card> cards = dealer.getCards();
        System.out.printf("%s카드: %s", name.getValue(), outputFormat.formatCard(cards.get(0)));
        System.out.println();
    }

    public static void printCard(Participant participant) {
        System.out.printf(outputFormat.formatCardSet(participant));
        System.out.println();
    }

    public static void printPlayerScore(Participant participant) {
        System.out.printf(outputFormat.formatCardResult(participant));
        System.out.println();
    }

    public static void printFinalResult(Map<Participant, Boolean> result) {
        System.out.println();
        System.out.println("## 최종 승패");
        System.out.printf(outputFormat.formatDealerResult(result));
        System.out.println();

        for (Map.Entry<Participant, Boolean> entry : result.entrySet()) {
            System.out.println(outputFormat.formatResult(entry));
        }
    }

    public static void printDealerHit() {
        System.out.println();
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
        System.out.println();
    }
}
