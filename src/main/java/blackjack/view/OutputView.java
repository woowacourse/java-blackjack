package blackjack.view;

import blackjack.domain.Record;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardCount;
import blackjack.vo.ParticipantVo;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    public static void printInitResult(List<String> names) {
        System.out.printf(System.lineSeparator() + "딜러와 %s에게 2장의 카드를 나누어주었습니다." + System.lineSeparator(),
                String.join(", ", names));
    }

    public static void printDealerFirstCard(Card card) {
        System.out.println("딜러: " + card.getNumber().getName() + card.getSymbol().getName());
    }

    public static void printPlayerCards(ParticipantVo vo) {
        List<String> list = vo.getCards().stream()
                .map(card -> card.getNumber().getName() + card.getSymbol().getName())
                .collect(Collectors.toList());

        System.out.println(vo.getName() + "카드: " + String.join(", ", list));
    }

    public static void printDealerDrawCardCount(CardCount cardCount) {
        if (cardCount.isDraw()) {
            System.out.println(System.lineSeparator() + "딜러는 16이하라 " + cardCount.getName() + "장의 카드를 더 받았습니다."
                    + System.lineSeparator());
            return;
        }

        System.out.println(System.lineSeparator() + "딜러가 16초과여서 카드를 받지않았습니다." + System.lineSeparator());
    }

    public static void printParticipantCards(ParticipantVo vo) {
        List<String> list = vo.getCards().stream()
                .map(card -> card.getNumber().getName() + card.getSymbol().getName())
                .collect(Collectors.toList());

        System.out.println(vo.getName() + "카드: " + String.join(", ", list)
                + " - 결과: " + vo.getScore());
    }

    public static void printDealerRecord(Map<Record, Integer> record) {
        System.out.println(System.lineSeparator() + "## 최종 승패");

        final StringBuilder builder = new StringBuilder();
        Arrays.stream(Record.values())
                .filter(it -> record.getOrDefault(it, 0) != 0)
                .forEach(it -> builder.append(record.get(it)).append(it.getName()));

        System.out.println("딜러: " + builder);
    }

    public static void printPlayerRecord(String name, Record record) {
        System.out.println(name + ": " + record.getName());
    }

    public static void printError(final String message) {
        System.out.println("[ERROR]: " + message);
    }
}
