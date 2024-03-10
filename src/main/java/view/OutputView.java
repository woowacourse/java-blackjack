package view;

import dto.PlayerDto;
import dto.ResultDto;
import dto.ScoreDto;
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
            stringBuilder.append(buildInitialHand(playerDto));
        }

        System.out.println(stringBuilder);
    }

    private String buildInitialHand(PlayerDto playerDto) {
        if (playerDto.name().equals("딜러")) {
            return playerDto.name() + ": " + playerDto.hands().get(0) + "\n";
        }

        return buildPlayerCards(playerDto) + "\n";
    }

    public void printHandAfterHit(PlayerDto playerDto) {
        System.out.println(buildPlayerCards(playerDto));
    }

    public void printDealerHitMessage() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printFinalHandAndScore(List<PlayerDto> playerDtos, ScoreDto scoreDto) {
        System.out.println();
        for (PlayerDto playerDto : playerDtos) {
            System.out.println(buildPlayerCardsWithResult(playerDto, scoreDto.getByPlayerName(playerDto.name())));
        }
    }

    private String buildPlayerCardsWithResult(PlayerDto playerDto, int score) {
        return String.format("%s - 결과: %d", buildPlayerCards(playerDto), score);
    }

    private String buildPlayerCards(PlayerDto playerDto) {
        String hands = String.join(", ", playerDto.hands());
        return playerDto.name() + ": " + hands;
    }

    public void printWinLoss(ResultDto resultDto) {
        System.out.println("\n## 최종 승패");
        printDealerWinLoss(resultDto.getDealerResult());
        printPlayerWinLoss(resultDto);
    }

    private void printDealerWinLoss(Map<String, Integer> result) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("딜러: ");
        stringBuilder.append(result.get("DEALER_WIN"))
                .append(convertDealerWinLoss("DEALER_WIN"));
        stringBuilder.append(result.get("PLAYER_WIN"))
                .append(convertDealerWinLoss("PLAYER_WIN"));
        if (result.get("PUSH") != 0) {
            stringBuilder.append(result.get("PUSH"))
                    .append(convertDealerWinLoss("PUSH"));
        }
        System.out.println(stringBuilder);
    }

    private void printPlayerWinLoss(ResultDto resultDto) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, String> playerResult : resultDto.getPlayerResult().entrySet()) {
            stringBuilder.append(playerResult.getKey())
                    .append(": ")
                    .append(convertPlayerWinLoss(playerResult.getValue()))
                    .append("\n");
        }
        System.out.println(stringBuilder);
    }

    private String convertPlayerWinLoss(String resultText) {
        if (resultText.equals("PLAYER_WIN")) {
            return "승";
        }
        if (resultText.equals("DEALER_WIN")) {
            return "패";
        }
        return "무";
    }

    private String convertDealerWinLoss(String resultText) {
        if (resultText.equals("PLAYER_WIN")) {
            return "패";
        }
        if (resultText.equals("DEALER_WIN")) {
            return "승";
        }
        return "무";
    }
}
