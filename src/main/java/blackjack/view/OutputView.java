package blackjack.view;

import java.util.stream.Collectors;

import blackjack.dto.CardDto;
import blackjack.dto.CurrentPlayerResponseDto;
import blackjack.dto.RoundResultsResponseDto;
import blackjack.dto.StartingCardsResponseDto;

public class OutputView {

    // TODO: toString, functional interface
    public static void printStartingCards(StartingCardsResponseDto responseDto) {
        String dealerName = responseDto.dealer().name();
        String playerNames = responseDto.players().stream()
            .map(StartingCardsResponseDto.InnerGamer::name)
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

    public static void printAdditionalCard(CurrentPlayerResponseDto responseDto) {
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
}
