package blackjack.view;

import blackjack.dto.CardDto;
import blackjack.dto.ParticipantDto;
import blackjack.dto.ProfitResultDto;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class OutputView {

    private OutputView() {
    }

    public static void printInitGameMessage(List<ParticipantDto> participantDtos, ParticipantDto dealerDto) {
        System.out.printf("%n%s와 %s에게 2장을 나누었습니다.", dealerDto.getName(),
                participantDtos.stream().map(ParticipantDto::getName).collect(Collectors.joining(", ")));
        System.out.println();
    }

    public static void printOpenCard(List<ParticipantDto> participantDtos, ParticipantDto dealerDto) {
        CardDto dealerOpenCard = dealerDto.getCards().get(0);
        System.out.printf("%s: %s%s", dealerDto.getName(), dealerOpenCard.getCardNumber(),
                dealerOpenCard.getCardPattern());
        System.out.println();
        for (ParticipantDto participantDto : participantDtos) {
            printPlayerCards(participantDto);
        }
    }

    public static void printPlayersResult(List<ParticipantDto> participantDtos, ParticipantDto dealerDto) {
        System.out.printf("%n");
        printPlayerResult(dealerDto);

        for (ParticipantDto participantDto : participantDtos) {
            printPlayerResult(participantDto);
        }
    }

    private static void printPlayerResult(ParticipantDto dealerDto) {
        System.out.printf("%s: %s - 결과: %d%n",
                dealerDto.getName(),
                dealerDto.getCards()
                        .stream()
                        .map(cardDto -> cardDto.getCardNumber() + cardDto.getCardPattern())
                        .collect(Collectors.joining(", ")),
                dealerDto.getTotalNumber());
    }

    public static void printPlayerCards(ParticipantDto participantDto) {
        System.out.printf("%s: %s%n", participantDto.getName(),
                participantDto.getCards()
                        .stream()
                        .map(cardDto -> cardDto.getCardNumber() + cardDto.getCardPattern())
                        .collect(Collectors.joining(", ")));
    }

    public static void printDealerDrawMessage() {
        System.out.printf("%n딜러는 16이하라 한장의 카드를 더 받았습니다.%n");
    }

    public static void printProfitResult(ProfitResultDto profitResultDto) {
        System.out.printf("%n## 최종 수익%n");
        System.out.printf("딜러: %d%n", Math.round(profitResultDto.getDealerProfit()));
        for (Entry<ParticipantDto, Double> entry : profitResultDto.getPlayersProfit().entrySet()) {
            System.out.printf("%s: %d%n", entry.getKey().getName(), Math.round(entry.getValue()));
        }
    }

}
