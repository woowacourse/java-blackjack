package view;

import dto.*;

import java.util.List;

public class OutputView {
    public void printStartGame(GameStartDTO gameStartDTO) {
        System.out.println("딜러와 " + convertListToString(gameStartDTO.playerNames()) + "에게 2장을 나누었습니다.");
        printDealerInitialHandCard(gameStartDTO.dealer());

        List<HandDTO> players = gameStartDTO.players();
        for (HandDTO player : players) {
            printHandCard(player);
        }
        System.out.println();
    }

    private void printDealerInitialHandCard(DealerInitialHandDTO dealerInitialHandDTO) {
        System.out.println("딜러카드: " + dealerInitialHandDTO.firstHandCard());
    }

    public void printHandCard(HandDTO playerHandDTO) {
        System.out.println(playerHandDTO.name() + "카드: " + convertListToString(playerHandDTO.handCards()));
    }

    public void printHandCardWithScore(HandScoreDTO handScoreDTO) {
        System.out.println(handScoreDTO.name() + "카드: " + convertListToString(handScoreDTO.handCards()) + " - 결과: " + handScoreDTO.score());
    }

    public void printDealerReceiveCard() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printScore(GameScoreDTO gameResultDTO) {
        System.out.println();
        printHandCardWithScore(gameResultDTO.dealer());

        List<HandScoreDTO> players = gameResultDTO.players();
        for (HandScoreDTO player : players) {
            printHandCardWithScore(player);
        }
    }
    public void printResults(GameResultDTO gameResultDTO) {
        DealerResultDTO dealerResultDTO = gameResultDTO.dealerResultDTO();
        List<PlayerResultDTO> playerResultDTOs = gameResultDTO.playerResultDTOs();
        System.out.println();
        System.out.println("## 최종 승패");
        System.out.println("딜러: " + dealerResultDTO.win() + "승 " + dealerResultDTO.draw() + "무 " + dealerResultDTO.lose() + "패");

        for (PlayerResultDTO playerResultDTO : playerResultDTOs) {
            System.out.println(playerResultDTO.name() + ": " + playerResultDTO.result());
        }
    }

    private String convertListToString(List<String> list) {
        return String.join(", ", list);
    }
}
