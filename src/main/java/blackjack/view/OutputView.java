package blackjack.view;

import blackjack.domain.GameResult;
import blackjack.domain.Player;
import blackjack.dto.DistributedCardDto;
import blackjack.dto.FinalResultDto;
import java.util.List;
import java.util.Map;

public class OutputView {
    public void displayCardDistribution(final DistributedCardDto dealerDto, final List<DistributedCardDto> playerDtos) {

        displayDistributionNotice(dealerDto, playerDtos);
        displayFirstCardOfDealer(dealerDto);
        playerDtos.forEach(this::displayCardInfo);
        System.out.println();
    }

    private void displayDistributionNotice(DistributedCardDto dealerDto, List<DistributedCardDto> playerDtos) {
        StringBuilder sb = new StringBuilder();
        sb.append(System.lineSeparator());
        sb.append(String.format("%s와 ", dealerDto.name()));
        String playerNames = String.join(", ", playerDtos.stream().map(player -> player.name().trim()).toList());
        sb.append(playerNames);
        sb.append("에게 2장을 나누었습니다.");
        System.out.println(sb);
    }

    private void displayFirstCardOfDealer(final DistributedCardDto dealerDto) {
        StringBuilder sb = new StringBuilder();
        sb.append(dealerDto.name() + "카드: ");
        sb.append(dealerDto.cardInfos().getFirst());

        System.out.println(sb);
    }

    public void displayCardInfo(final DistributedCardDto participantDto) {
        StringBuilder sb = new StringBuilder();
        sb.append(participantDto.name() + "카드: ");
        sb.append(String.join(", ", participantDto.cardInfos()));

        System.out.println(sb);
    }

    public void displayExtraDealerCardStatus() {
        System.out.println();
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void displayFinalCardStatus(final FinalResultDto dealerDto, final List<FinalResultDto> playerDtos) {
        System.out.println();
        displayResultInfo(dealerDto);
        playerDtos.forEach(this::displayResultInfo);
    }

    private void displayResultInfo(final FinalResultDto finalResultDto) {
        StringBuilder sb = new StringBuilder();

        sb.append(finalResultDto.name())
                .append("카드: ")
                .append(String.join(", ", finalResultDto.cardInfos()));
        sb.append(" - 결과: ").append(finalResultDto.score());

        if (finalResultDto.isBust()) {
            sb.append(" (버스트)");
        }

        System.out.println(sb);
    }

    public void displayDealerResult(final Map<GameResult, Integer> dealerResult) {
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

    public void displayPlayerResult(final Player player, final GameResult playerResult) {
        System.out.println(player.getName().trim() + ": " + playerResult.getStatus());
    }

    public void displayBustNotice() {
        System.out.println("버스트이기 때문에 카드를 더 받을 수 없습니다.");
    }
}
