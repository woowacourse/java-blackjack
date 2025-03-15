package blackjack.view;

import blackjack.dto.GamerDto;
import blackjack.dto.response.ProfitsResponseDto;
import blackjack.dto.response.RoundResultsResponseDto;
import blackjack.dto.response.StartingCardsResponseDto;

public class OutputView {

    private OutputView() {
    }

    public static void printStartingCards(StartingCardsResponseDto responseDto) {
        System.out.printf("%s와 %s에게 %d장을 나누었습니다.%n",
                responseDto.getDealerName(),
                responseDto.getPlayerNames(),
                responseDto.startingCardsSize());
        System.out.println(responseDto.dealer());
        for (var player : responseDto.players()) {
            System.out.println(player);
        }
    }

    public static void printAdditionalCard(GamerDto responseDto) {
        System.out.println(responseDto);
    }

    public static void printBustNotice(String name) {
        System.out.printf("%s는 버스트되었습니다.%n", name);
    }

    public static void printDealerDrawNotice() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printRoundResult(RoundResultsResponseDto responseDto) {
        System.out.println(responseDto.dealer());
        for (var player : responseDto.players()) {
            System.out.println(player);
        }
    }

    public static void printFinalResult(ProfitsResponseDto responseDto) {
        System.out.println("## 최종 수익");
        for (var gamer : responseDto.gamers()) {
            System.out.println(gamer);
        }
    }
}
