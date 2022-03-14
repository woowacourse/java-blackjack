package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import blackjack.domain.Record;
import blackjack.domain.card.Card;
import blackjack.domain.participant.DrawCount;
import blackjack.dto.ParticipantDto;

public class OutputView {

    private OutputView() {
    }

    public static void printInitResult(List<String> names) {
        printEmptyLine();
        System.out.printf("딜러와 %s에게 2장의 카드를 나누어주었습니다.",
            String.join(", ", names));
        printEmptyLine();
    }

    public static void printDealerFirstCard(Card card) {
        System.out.println("딜러: " + card.getNumber().getName() + card.getSymbol().getName());
    }

    public static void printPlayerCards(ParticipantDto dto) {
        System.out.println(dto.getName() + "카드: " + String.join(", ", getCardsStatus(dto)));
    }

    private static List<String> getCardsStatus(ParticipantDto dto) {
        return dto.getCards().stream()
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

    public static void printParticipantCardsWithScore(ParticipantDto dto) {
        System.out.println(dto.getName() + "카드: " + String.join(", ", getCardsStatus(dto))
            + " - 결과: " + dto.getScore());
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
