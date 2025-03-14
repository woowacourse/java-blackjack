package blackjack.view;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import blackjack.dto.GamerDto;
import blackjack.dto.card.CardDto;
import blackjack.dto.card.CardsDto;
import blackjack.dto.response.FinalResultResponseDto;
import blackjack.dto.response.ProfitResponseDto;
import blackjack.dto.response.RoundResultsResponseDto;
import blackjack.dto.response.StartingCardsResponseDto;

public class OutputView {

    private OutputView() {
    }

    public static void printStartingCards(StartingCardsResponseDto responseDto) {
        System.out.printf("%s와 %s에게 %d장을 나누었습니다.%n",
            responseDto.dealer().name(),
            gamerNameJoining(responseDto.players()),
            responseDto.startingCardsSize());
        System.out.println(toString(responseDto.dealer()));
        for (var player : responseDto.players()) {
            System.out.println(toString(player));
        }
    }

    public static void printAdditionalCard(GamerDto responseDto) {
        System.out.println(toString(responseDto));
    }

    public static void printBustNotice(String name) {
        System.out.printf("%s는 버스트되었습니다.%n", name);
    }

    public static void printDealerDrawNotice() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printRoundResult(RoundResultsResponseDto responseDto) {
        System.out.println(toString(responseDto.dealer()));
        for (var player : responseDto.players()) {
            System.out.println(toString(player));
        }
    }

    public static void printFinalResult(FinalResultResponseDto responseDto) {
        System.out.println("## 최종 승패");
        for (var gamer : responseDto.gamers()) {
            System.out.println(toString(gamer));
        }
    }

    public static void printProfit(ProfitResponseDto dto) {
        System.out.println("## 최종 수익");
        for (var gamerProfit : dto.profits().entrySet()) {
            System.out.printf("%s: %.0f%n", gamerProfit.getKey(), gamerProfit.getValue());
        }
    }

    private static String toString(CardDto dto) {
        return dto.number().getDisplayName() + dto.type().getDisplayName();
    }

    private static String toString(CardsDto dto) {
        return dto.cards().stream()
            .map(OutputView::toString)
            .collect(Collectors.joining(", "));
    }

    private static String toString(GamerDto dto) {
        return String.format("%s카드 : %s", dto.name(), toString(dto.cards()));
    }

    private static String toString(RoundResultsResponseDto.InnerGamer dto) {
        return String.format("%s - 결과: %d", toString(dto.gamer()), dto.sumOfCards());
    }

    private static String toString(FinalResultResponseDto.InnerGamer dto) {
        return String.format("%s: %s",
            dto.name(),
            dto.result().entrySet().stream()
                .filter(result -> result.getValue() > 0)
                .sorted(Comparator.comparing(result -> result.getKey().ordinal()))
                .map(result -> result.getValue() + result.getKey().getDisplayName())
                .collect(Collectors.joining(" ")));
    }

    private static String gamerNameJoining(List<GamerDto> dtos) {
        return dtos.stream()
            .map(GamerDto::name)
            .collect(Collectors.joining(", "));
    }
}
