package view;

import dto.*;

import java.util.List;

public class OutputView {
    public void printStartGame(GameStartDto gameStartDto) {
        System.out.println("딜러와 " + convertListToString(gameStartDto.playerNames()) + "에게 2장을 나누었습니다.");
        printDealerInitialHandCard(gameStartDto.dealer());

        List<HandDto> players = gameStartDto.players();
        for (HandDto player : players) {
            printHandCard(player);
        }
        System.out.println();
    }

    private void printDealerInitialHandCard(DealerInitialHandDto dealerInitialHandDto) {
        System.out.println("딜러카드: " + dealerInitialHandDto.firstHandCard());
    }

    public void printHandCard(HandDto playerHandDto) {
        System.out.println(playerHandDto.name() + "카드: " + convertListToString(playerHandDto.handCards()));
    }

    public void printHandCardWithScore(HandScoreDto handScoreDto) {
        System.out.println(handScoreDto.name() + "카드: " + convertListToString(handScoreDto.handCards()) + " - 결과: " + handScoreDto.score());
    }

    public void printDealerReceiveCard() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printScore(GameScoreDto gameResultDto) {
        System.out.println();
        printHandCardWithScore(gameResultDto.dealer());

        List<HandScoreDto> players = gameResultDto.players();
        for (HandScoreDto player : players) {
            printHandCardWithScore(player);
        }
    }
    public void printResults(GameResultDto gameResultDto) {
        DealerResultDto dealerResultDto = gameResultDto.dealerResultDto();
        List<PlayerResultDto> playerResultDtos = gameResultDto.playerResultDtos();
        System.out.println();
        System.out.println("## 최종 승패");
        System.out.println("딜러: " + dealerResultDto.win() + "승 " + dealerResultDto.draw() + "무 " + dealerResultDto.draw()+"패");

        for (PlayerResultDto playerResultDto : playerResultDtos) {
            System.out.println(playerResultDto.name() + ": " + playerResultDto.result());
        }
    }

    private String convertListToString(List<String> list) {
        return String.join(", ", list);
    }

    public void printErrorMessage(String errorMessage) {
        System.out.printf("%s 다시 입력해주세요\n", errorMessage);
    }
}
