package view;

import dto.PlayerDto;
import dto.ProfitDto;
import dto.ScoreDto;
import java.util.List;
import java.util.Map;

public class OutputView {

    public void printInitialDeal(final List<PlayerDto> playerDtos) {
        List<String> playerNames = playerDtos.stream().map(PlayerDto::name).toList();
        String joinedPlayerNames = String.join(", ", playerNames);
        String message = String.format("%s에게 2장을 나누었습니다.", joinedPlayerNames);
        System.out.println(message);
    }

    public void printInitialHand(final PlayerDto playerDto) {
        System.out.println(buildInitialHand(playerDto));
    }

    public void printInitialHand(final List<PlayerDto> playerDtos) {
        StringBuilder stringBuilder = new StringBuilder();
        for (PlayerDto playerDto : playerDtos) {
            stringBuilder.append(buildInitialHand(playerDto)).append("\n");
        }

        System.out.println(stringBuilder);
    }

    private String buildInitialHand(final PlayerDto playerDto) {
        if (playerDto.name().equals("딜러")) {
            return playerDto.name() + "카드: " + playerDto.hands().get(0);
        }

        return buildPlayerCards(playerDto);
    }

    public void printHandAfterHit(final PlayerDto playerDto) {
        System.out.println(buildPlayerCards(playerDto));
    }

    public void printDealerHitMessage() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.\n");
    }

    public void printFinalHandAndScore(final List<PlayerDto> playerDtos, final ScoreDto scoreDto) {
        System.out.println();
        for (PlayerDto playerDto : playerDtos) {
            System.out.println(buildPlayerCardsWithResult(playerDto, scoreDto.getByPlayerName(playerDto.name())));
        }
    }

    private String buildPlayerCardsWithResult(final PlayerDto playerDto, final int score) {
        return String.format("%s - 결과: %d", buildPlayerCards(playerDto), score);
    }

    private String buildPlayerCards(final PlayerDto playerDto) {
        return playerDto.name() + "카드: " + String.join(", ", playerDto.hands());
    }

    public void printProfits(final ProfitDto profitDto) {
        System.out.println("\n## 최종 수익");
        System.out.println(profitDto.dealerProfit().getKey() + ": " + profitDto.dealerProfit().getValue());
        for (Map.Entry<String, Integer> entry : profitDto.playerProfits().entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

}
