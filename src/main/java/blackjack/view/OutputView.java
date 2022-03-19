package blackjack.view;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import blackjack.domain.DrawCount;
import blackjack.domain.card.Card;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Participant;

public final class OutputView {

    private OutputView() {
    }

    public static void printInitResult(List<Name> names) {
        printEmptyLine();
        System.out.printf("딜러와 %s에게 2장의 카드를 나누어주었습니다.",
            names.stream().map(Name::getValue).collect(Collectors.joining(", ")));
        printEmptyLine();
    }

    public static void printParticipantCards(Participant participant) {
        if (participant instanceof Dealer) {
            printDealerFirstCard(participant);
            return;
        }
        System.out.println(participant.getName().getValue() + "카드: "
            + String.join(", ", getCardsStatus(participant)));
    }

    private static void printDealerFirstCard(Participant participant) {
        Card card = participant.getCards().findFirst();
        System.out.println(participant.getName().getValue() + ": "
            + card.getNumber().getName() + card.getSymbol().getName());
    }

    private static List<String> getCardsStatus(Participant participant) {
        return participant.getCards().getValue().stream()
            .map(card -> card.getNumber().getName() + card.getSymbol().getName())
            .collect(Collectors.toList());
    }

    public static void printDealerDrawCardCount(DrawCount drawCount) {
        printEmptyLine();
        System.out.println(getDealerDrawString(drawCount));
        printEmptyLine();
    }

    private static String getDealerDrawString(DrawCount drawCount) {
        if (drawCount.isDraw()) {
            return "딜러는 16이하라 " + drawCount.getName() + "장의 카드를 더 받았습니다.";
        }
        return "딜러가 16초과여서 카드를 받지않았습니다.";
    }

    public static void printParticipantCardsWithScore(Participant participant) {
        System.out.println(participant.getName().getValue() + "카드: " + String.join(", ", getCardsStatus(participant))
            + " - 결과: " + participant.getScore());
    }

    public static void printFinalRevenues(Map<Name, Long> revenues) {
        printEmptyLine();
        System.out.println("## 최종 수익");
        for (Map.Entry<Name, Long> entry : revenues.entrySet()) {
            System.out.println(entry.getKey().getValue() + ": " + entry.getValue());
        }
    }

    public static void printEmptyLine() {
        System.out.println();
    }
}
