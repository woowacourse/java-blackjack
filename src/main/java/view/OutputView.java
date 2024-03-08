package view;

import dto.DealerResultDto;
import dto.PlayerDto;
import dto.PlayersResultDto;

import java.util.List;
import java.util.Map;

public class OutputView {

    public void printInitialDeal(List<PlayerDto> playerDtos) {
        List<String> playerNames = playerDtos.stream().map(PlayerDto::name).toList();
        String joinedPlayerNames = String.join(", ", playerNames);
        String message = String.format("%s에게 2장을 나누었습니다.", joinedPlayerNames);
        System.out.println(message);
    }

    public void printInitialHand(List<PlayerDto> playerDtos) {
        StringBuilder stringBuilder = new StringBuilder();
        for (PlayerDto playerDto : playerDtos) {
            //TODO: 딜러 한장만 보여주기
            stringBuilder.append(buildPlayerCards(playerDto));
            stringBuilder.append("\n");
        }

        System.out.println(stringBuilder);
    }

    public void printHandAfterHit(PlayerDto playerDto) {
        System.out.println(buildPlayerCards(playerDto));
    }

    public void printDealerHitMessage() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printFinalHandAndScore(List<PlayerDto> playerDtos) {
        for (PlayerDto playerDto : playerDtos) {
            System.out.println(buildPlayerCardsWithResult(playerDto));
        }
    }

    private String buildPlayerCardsWithResult(PlayerDto playerDto) {
        return buildPlayerCards(playerDto) + " - 결과: " + playerDto.score();
    }

    private String buildPlayerCards(PlayerDto playerDto) {
        String hands = String.join(", ", playerDto.hands());
        return playerDto.name() + ": " + hands;
    }

    public void printWinLoss(DealerResultDto dealerResultDto, PlayersResultDto playersResultDto) {
        System.out.println("## 최종 승패");
        printDealerWinLoss(dealerResultDto);
        printPlayerWinLoss(playersResultDto);
    }

    private void printDealerWinLoss(DealerResultDto dealerResultDto) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("딜러: ");
        stringBuilder.append(dealerResultDto.winCount() + "승 ");
        stringBuilder.append(dealerResultDto.lossCount() + "패 ");
        if (dealerResultDto.pushCount() != 0) {
            stringBuilder.append(dealerResultDto.winCount() + "무");
        }
        System.out.println(stringBuilder);
    }

    private void printPlayerWinLoss(PlayersResultDto playersResultDto) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, String> playerResult : playersResultDto.playerResults().entrySet()) {
            stringBuilder.append(playerResult.getKey() + ": " + playerResult.getValue() + "\n");
        }
        System.out.println(stringBuilder);
    }
}
