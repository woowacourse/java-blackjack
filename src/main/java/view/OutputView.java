package view;

import domain.GameResult;
import dto.GameScoreDTO;
import dto.ParticipantHandDTO;
import dto.GameStartDTO;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class OutputView {
    public void printStartGame(GameStartDTO startGameDTO) {
        System.out.println("딜러와 " + startGameDTO.getPlayerNames() + "에게 2장을 나누었습니다.");

        List<ParticipantHandDTO> players = startGameDTO.getPlayers();
        for (ParticipantHandDTO player : players) {
            printHandCard(player);
        }
        printHandCard(startGameDTO.getDealer());
    }

    public void printHandCard(ParticipantHandDTO participantHandDTO) {
        System.out.println(participantHandDTO.getName() + "카드: " + participantHandDTO.getHandCards());
    }

    public void printHandCardWithScore(ParticipantHandDTO participantHandDTO) {
        System.out.println(participantHandDTO.getName() + "카드: " + participantHandDTO.getHandCards() + " - 결과: " + participantHandDTO.getScore());
    }

    public void printDealerReceiveCard() {
        System.out.println();
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printFinalScore(GameScoreDTO gameResultDTO) {
        System.out.println();
        printHandCardWithScore(gameResultDTO.getDealer());

        List<ParticipantHandDTO> players = gameResultDTO.getPlayers();
        for (ParticipantHandDTO player : players) {
            printHandCardWithScore(player);
        }
    }

    public void printDealerFinalCount(int dealerWinningCount, int dealerLosingCount) {
        System.out.println();
        System.out.println("## 최종 승패");
        System.out.println("딜러: " + dealerWinningCount + "승 " + dealerLosingCount + "패");
    }

    public void printPlayerFinalResults(Map<String, GameResult> playerFinalResults) {
        for (Entry<String, GameResult> playerFinalResult : playerFinalResults.entrySet()) {
            System.out.println(playerFinalResult.getKey() + ": " + playerFinalResult.getValue().getValue());
        }
    }
}
