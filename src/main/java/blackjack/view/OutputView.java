package blackjack.view;

import blackjack.domain.GameResult;
import blackjack.domain.Player;
import blackjack.dto.DistributedCardDto;
import blackjack.dto.FinalResultDto;
import java.util.List;
import java.util.Map;

public class OutputView {
    public void displayDistributedCardStatus(DistributedCardDto dealerDto, List<DistributedCardDto> playerDtos) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s와 ", dealerDto.name()));
        String playerNames = String.join(", ", playerDtos.stream().map(player -> player.name().trim()).toList());
        sb.append(playerNames);
        sb.append("에게 2장을 나누었습니다.\n");
        System.out.println(sb);

        displayCardInfo(dealerDto);
        for (DistributedCardDto dto : playerDtos) {
            displayCardInfo(dto);
        }
        System.out.println();
    }

    public void displayCardInfo(DistributedCardDto participantDto) {
        StringBuilder sb = new StringBuilder();
        sb.append(participantDto.name() + "카드: ");
        sb.append(String.join(", ", participantDto.cardInfos()));

        System.out.println(sb);
    }

    public void displayExtraDealerCardStatus() {
        System.out.println();
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void displayFinalCardStatus(FinalResultDto dealerDto, List<FinalResultDto> playerDtos) {
        System.out.println();
        displayResultInfo(dealerDto);
        playerDtos.forEach(this::displayResultInfo);
    }

    private void displayResultInfo(FinalResultDto finalResultDto) {
        System.out.print(finalResultDto.name() + "카드: " + String.join(", ", finalResultDto.cardInfos()));
        System.out.println(" - 결과: " + finalResultDto.score());
    }

    public void displayDealerResult(Map<GameResult, Integer> dealerResult) {
        System.out.println();
        StringBuilder sb = new StringBuilder();
        sb.append("## 최종 승패\n");

        sb.append("딜러: ");
        for (GameResult gameResult : GameResult.values()) {
            if (!dealerResult.containsKey(gameResult)) {
                continue;
            }
            sb.append(String.format("%d%s ", dealerResult.get(gameResult), gameResult.getStatus()));
        }

        System.out.println(sb);
    }

    public void displayPlayerResult(Player player, GameResult playerResult) {
        System.out.println(player.getName() + ": " + playerResult.getStatus());
    }
}
