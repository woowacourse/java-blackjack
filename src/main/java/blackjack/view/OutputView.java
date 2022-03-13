package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import blackjack.domain.Record;
import blackjack.domain.card.Card;
import blackjack.domain.participant.DrawCount;
import blackjack.vo.ParticipantVo;

public class OutputView {

    public static void printInitResult(List<String> names) {
        printEmptyLine();
        System.out.printf("딜러와 %s에게 2장의 카드를 나누어주었습니다.",
            String.join(", ", names));
        printEmptyLine();
    }

    public static void printDealerFirstCard(Card card) {
        System.out.println("딜러: " + card.getNumber().getName() + card.getSymbol().getName());
    }

    public static void printPlayerCards(ParticipantVo vo) {
        System.out.println(vo.getName() + "카드: " + String.join(", ", getCardsStatus(vo)));
    }

    private static List<String> getCardsStatus(ParticipantVo vo) {
        return vo.getCards().stream()
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

    public static void printParticipantCardsWithScore(ParticipantVo vo) {
        System.out.println(vo.getName() + "카드: " + String.join(", ", getCardsStatus(vo))
            + " - 결과: " + vo.getScore());
    }

    public static void printDealerRecord(Map<Record, Integer> record) {
        printEmptyLine();
        System.out.println("## 최종 승패");

        StringBuilder builder = new StringBuilder();
        Arrays.stream(Record.values())
            .filter(it -> record.getOrDefault(it, 0) != 0)
            .forEach(it -> builder.append(record.get(it)).append(it.getName()));

        System.out.println("딜러: " + builder);
    }

    public static void printPlayerRecord(String name, Record record) {
        System.out.println(name + ": " + record.getName());
    }

    public static void printEmptyLine() {
        System.out.println();
    }
}
