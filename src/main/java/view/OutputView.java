package view;

import dto.DealerResultDTO;
import dto.GameScoreDTO;
import dto.GameStartDTO;
import dto.ParticipantHandDTO;
import dto.GameResultDTO;
import dto.PlayerResultDTO;

import java.util.List;

public class OutputView {
    public void printStartGame(GameStartDTO startGameDTO) {
        System.out.println("딜러와 " + startGameDTO.getPlayerNames() + "에게 2장을 나누었습니다.");
        printHandCard(startGameDTO.getDealer());

        List<ParticipantHandDTO> players = startGameDTO.getPlayers();
        for (ParticipantHandDTO player : players) {
            printHandCard(player);
        }
        System.out.println();
    }

    public void printHandCard(ParticipantHandDTO participantHandDTO) {
        System.out.println(participantHandDTO.getName() + "카드: " + participantHandDTO.getHandCards());
    }

    public void printHandCardWithScore(ParticipantHandDTO participantHandDTO) {
        System.out.println(participantHandDTO.getName() + "카드: " + participantHandDTO.getHandCards() + " - 결과: " + participantHandDTO.getScore());
    }

    public void printDealerReceiveCard() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printScore(GameScoreDTO gameResultDTO) {
        System.out.println();
        printHandCardWithScore(gameResultDTO.getDealer());

        List<ParticipantHandDTO> players = gameResultDTO.getPlayers();
        for (ParticipantHandDTO player : players) {
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
}
