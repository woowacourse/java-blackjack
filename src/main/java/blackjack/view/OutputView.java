package blackjack.view;

import blackjack.domain.Record;
import blackjack.dto.CardDto;
import blackjack.dto.DealerTurnResultDto;
import blackjack.dto.ParticipantDto;
import blackjack.dto.ParticipantResultDto;
import blackjack.dto.RecordDto;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OutputView {

    public static void printPrepareResult(List<String> names) {
        System.out.printf(System.lineSeparator() + "딜러와 %s에게 2장의 카드를 나누어주었습니다." + System.lineSeparator(),
                String.join(", ", names));
    }

    public static void printDealerFirstCard(CardDto cardDto) {
        System.out.println("딜러: " + cardDto.getNumberName() + cardDto.getSymbolName());
    }

    public static void printPlayerCards(ParticipantDto dto) {
        List<String> list = dto.getCardDtos().stream()
                .map(card -> card.getNumberName() + card.getSymbolName())
                .collect(Collectors.toList());

        System.out.println(dto.getName() + "카드: " + String.join(", ", list));
    }

    public static void printDealerTurnResult(final DealerTurnResultDto dealerTurnResultDto) {
        final int count = dealerTurnResultDto.getCount();
        if (count == 0) {
            printDealerNotDrawMessage();
            return;
        }

        IntStream.range(0, count)
                .forEach(i -> printDealerDrawMessage());
    }

    private static void printDealerDrawMessage() {
        System.out.println(System.lineSeparator() + "딜러는 16이하라 한 장의 카드를 더 받았습니다.");
    }

    private static void printDealerNotDrawMessage() {
        System.out.println(System.lineSeparator() + "딜러가 16초과여서 카드를 받지않았습니다.");
    }

    public static void printParticipantCards(ParticipantResultDto dto) {
        List<String> list = dto.getCardDtos().stream()
                .map(card -> card.getNumberName() + card.getSymbolName())
                .collect(Collectors.toList());

        System.out.println(dto.getName() + "카드: " + String.join(", ", list)
                + " - 결과: " + dto.getScore());
    }

    public static void printRecord(RecordDto dto) {
        printDealerRecord(dto.getDealerRecord());
        dto.getPlayerRecord().forEach(OutputView::printPlayerRecord);
    }

    private static void printDealerRecord(Map<Record, Integer> record) {
        System.out.println(System.lineSeparator() + "## 최종 승패");

        final StringBuilder builder = new StringBuilder();
        Arrays.stream(Record.values())
                .filter(it -> record.getOrDefault(it, 0) != 0)
                .forEach(it -> builder.append(record.get(it)).append(it.getName()));

        System.out.println("딜러: " + builder);
    }

    private static void printPlayerRecord(String playerName, Record record) {
        System.out.println(playerName + ": " + record.getName());
    }

    public static void printError(final String message) {
        System.out.println("[ERROR]: " + message);
    }

    public static void breakLine() {
        System.out.println();
    }
}
