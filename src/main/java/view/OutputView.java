package view;

import domain.GameResult;
import dto.ParticipantDto;
import dto.PlayerResultDto;
import java.util.List;
import java.util.Map;

public class OutputView {

    public void printStartCardMessage(List<String> playerNames) {
        System.out.println();
        System.out.println("딜러와 " + String.join(", ", playerNames) + "에게 2장을 나누었습니다.");
    }

    public void printDealerStartCard(String firstCard) {
        System.out.println("딜러카드: " + firstCard);
    }

    public void printStartCard(List<ParticipantDto> playerDtos) {
        for (ParticipantDto participant : playerDtos) {
            printCurrentHoldCard(participant);
        }
        System.out.println();
    }

    public void printCurrentHoldCard(ParticipantDto dto) {
        System.out.println(dto.name() + "카드: " + dto.cards());
    }

    public void printDealerReceiveCard() {
        System.out.println();
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printFinalScore(ParticipantDto dealerDto, List<ParticipantDto> playerDtos) {
        System.out.println();
        printParticipantScore(dealerDto);
        for (ParticipantDto participant : playerDtos) {
            printParticipantScore(participant);
        }
    }

    public void printDealerFinalCount(Map<GameResult, Integer> dealerResults) {
        System.out.println();
        System.out.println("## 최종 승패");
        System.out.println("딜러: "
                + dealerResults.getOrDefault(GameResult.WIN, 0) + "승 "
                + dealerResults.getOrDefault(GameResult.DRAW, 0) + "무 "
                + dealerResults.getOrDefault(GameResult.LOSE, 0) + "패");
    }

    public void printPlayerFinalResults(List<PlayerResultDto> playerResultDtos) {
        for (PlayerResultDto playerResult : playerResultDtos) {
            System.out.println(playerResult.name() + ": " + playerResult.result());
        }
    }

    private void printParticipantScore(ParticipantDto participant) {
        System.out.println(participant.name() + "카드: " + participant.cards() + " - 결과: " + participant.score());
    }
}
