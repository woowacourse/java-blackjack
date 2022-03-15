package blackjack.view;

import blackjack.view.dto.ParticipantDto;
import blackjack.view.dto.PlayersDto;

public class OutputView {

    public static void printInitCardHandStatus(ParticipantDto dealerDto, PlayersDto playersDto) {
        System.out.println();
        System.out.println("딜러와 " + playersDto.showNames() + "에게 2장을 나누었습니다.");

        System.out.println("딜러: " + dealerDto.showOneCard());
        System.out.println(playersDto.showEachCards());
        System.out.println();
    }

    public static String showCardHandStatus(ParticipantDto participantDto) {
        return String.format("%s 카드: %s",
                participantDto.getName(),
                participantDto.showCards());
    }

    public static void printDealerStatus() {
        System.out.println();
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printCardHandStatus(ParticipantDto participantDto) {
        System.out.println(showCardHandStatus(participantDto));
    }

    public static void printFinalStatus(ParticipantDto dealerDto, PlayersDto playersDto) {
        System.out.println(showCardHandStatus(dealerDto) + " 결과 - " + dealerDto.getScore());
        System.out.println(playersDto.showEachResult());
    }

    public static void printFinalResult(ParticipantDto dealerDto, PlayersDto playersDto) {
        System.out.println();
        System.out.println("## 최종 승패");
//        Map<Player, Result> resultCounter = playersDto.judgeResult(dealerDto.calculateBestScore());
//        System.out.println(dealerDto.getName() + ": " +
//                resultCounter.values().stream().filter(result -> result == Result.WIN).count() + "승" +
//                resultCounter.values().stream().filter(result -> result == Result.TIE).count() + "무" +
//                resultCounter.values().stream().filter(result -> result == Result.LOSE).count() + "패");
//        resultCounter.forEach((player, result)
//                -> System.out.println(player.getName().getValue() + ": " + result.getResult()));
    }
}
