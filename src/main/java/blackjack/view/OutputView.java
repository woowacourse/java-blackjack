package blackjack.view;

import java.util.stream.Collectors;

import blackjack.dto.CardDto;
import blackjack.dto.GamerDto;
import blackjack.dto.response.FinalResultResponseDto;
import blackjack.dto.response.RoundResultsResponseDto;
import blackjack.dto.response.StartingCardsResponseDto;

// LAST TODO 메시지 상수화
public class OutputView {

    // TODO: toString, functional interface, printf
    public static void printStartingCards(StartingCardsResponseDto responseDto) {
        String dealerName = responseDto.dealer().name();
        String playerNames = responseDto.players().stream()
            .map(GamerDto::name)
            .collect(Collectors.joining(", "));
        int cardCount = responseDto.startingCardsSize();

        System.out.println(String.format("%s와 %s에게 %d장을 나누었습니다.", dealerName, playerNames, cardCount));

        CardDto dealerCard = responseDto.dealer().cards().getLast();
        String dealerCardName = dealerCard.number() + dealerCard.type();
        System.out.println(String.format("%s카드 : %s", dealerName, dealerCardName));

        for (var player : responseDto.players()) {
            String playerName = player.name();
            String playerCardNames = player.cards().stream()
                .map(card -> card.number() + card.type())
                .collect(Collectors.joining(", "));
            System.out.println(String.format("%s카드 : %s", playerName, playerCardNames));
        }
    }

    public static void printAdditionalCard(GamerDto responseDto) {
        System.out.println(String.format("%s카드 : %s",
            responseDto.name(),
            responseDto.cards().stream()
                .map(card -> card.number() + card.type())
                .collect(Collectors.joining(", "))));
    }

    public static void printBustNotice(String name) {
        System.out.printf("%s는 버스트되었습니다.%n", name);
    }

    public static void printDealerDrawNotice() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    // TODO 중복되는 코드 전부 개선하기
    public static void printRoundResult(RoundResultsResponseDto responseDto) {
        System.out.println(String.format("%s카드 : %s - 결과: %d",
            responseDto.dealer().name(),
            responseDto.dealer().cards().stream()
                .map(card -> card.number() + card.type())
                .collect(Collectors.joining(", ")),
            responseDto.dealer().sumOfCards()));

        for (var player : responseDto.players()) {
            String playerName = player.name();
            String playerCardNames = player.cards().stream()
                .map(card -> card.number() + card.type())
                .collect(Collectors.joining(", "));
            System.out.println(String.format("%s카드 : %s - 결과: %d", playerName, playerCardNames, player.sumOfCards()));
        }
    }

    public static void printFinalResult(FinalResultResponseDto responseDto) {
        System.out.println("## 최종 승패");
        for (var gamer : responseDto.gamers()) {
            System.out.println(String.format("%s: %s",
                gamer.name(),
                gamer.result().entrySet().stream()
                    .filter(result -> result.getValue() > 0)
                    .map(result -> result.getValue() + result.getKey().getDisplayName())
                    .collect(Collectors.joining(" "))
            ));
        }
    }
}
