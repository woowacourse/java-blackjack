package blackjack.view;

import java.util.stream.Collectors;

import blackjack.dto.CardDto;
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

        for (StartingCardsResponseDto.InnerGamer player : responseDto.players()) {
            String playerName = player.name();
            String playerCardNames = player.cards().stream()
                .map(card -> card.number() + card.type())
                .collect(Collectors.joining(", "));
            System.out.println(String.format("%s카드 : %s", playerName, playerCardNames));
        }
    }
}
