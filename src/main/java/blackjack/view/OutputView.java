package blackjack.view;

import blackjack.dto.CardDto;
import blackjack.dto.DealerRecordDto;
import blackjack.dto.DealerTurnResultDto;
import blackjack.dto.ParticipantDto;
import blackjack.dto.ParticipantResultDto;
import blackjack.dto.PlayerRecordDto;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OutputView {

    public static void printAssignmentResult(List<String> names) {
        System.out.printf(System.lineSeparator() + "딜러와 %s에게 2장의 카드를 나누어주었습니다." + System.lineSeparator(),
                String.join(", ", names));
    }

    public static void printDealerFirstCard(CardDto cardDto) {
        System.out.println("딜러: " + cardDto.getNumberName() + cardDto.getSymbolName());
    }

    public static void printCards(ParticipantDto dto) {
        System.out.println(dto.getName() + "카드: " + toCardMessage(dto));
    }

    public static void printCardsAndScore(ParticipantResultDto dto) {
        final ParticipantDto participantDto = dto.getParticipantDto();

        System.out.println(participantDto.getName() + "카드: " + toCardMessage(participantDto)
                + " - 결과: " + dto.getScore());
    }

    private static String toCardMessage(final ParticipantDto dto) {
        return dto.getCardDtos().stream()
                .map(card -> card.getNumberName() + card.getSymbolName())
                .collect(Collectors.joining(", "));
    }

    public static void printDealerTurnResult(final DealerTurnResultDto dealerTurnResultDto) {
        final int drawCount = dealerTurnResultDto.getCount();
        if (drawCount == 0) {
            printDealerNotDrawMessage();
            return;
        }

        IntStream.range(0, drawCount)
                .forEach(i -> printDealerDrawMessage());
    }

    private static void printDealerDrawMessage() {
        System.out.println(System.lineSeparator() + "딜러는 16이하라 한 장의 카드를 더 받았습니다.");
    }

    private static void printDealerNotDrawMessage() {
        System.out.println(System.lineSeparator() + "딜러가 16초과여서 카드를 받지않았습니다.");
    }

    public static void printDealerRecord(DealerRecordDto dto) {
        System.out.println(System.lineSeparator() + "## 최종 승패");

        final String message = dto.getKeys()
                .stream()
                .filter(key -> dto.getValue(key) != 0)
                .map(key -> dto.getValue(key) + key)
                .collect(Collectors.joining(" "));

        System.out.println("딜러: " + message);
    }

    public static void printPlayerRecord(PlayerRecordDto dto) {
        System.out.println(dto.getName() + ": " + dto.getRecord().getName());
    }

    public static void printError(final String message) {
        System.out.println("[ERROR]: " + message);
    }

    public static void breakLine() {
        System.out.println();
    }
}
