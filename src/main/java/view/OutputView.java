package view;

import domain.Player;
import domain.Result;
import dto.PlayerDto;

import java.util.List;
import java.util.Map;

public class OutputView {

    public void printDealingMessage(List<PlayerDto> playerDtos) {
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
        System.out.println();
    }

    public void printHansAfterHit(PlayerDto playerDto) {
        System.out.println(buildPlayerCards(playerDto));
        System.out.println();
    }

    public void printDealerHitMessage() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
        System.out.println();
    }

    public void printGameScore(List<PlayerDto> playerDtos) {
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
    // List<GameResult> -> String name / String Result => 승 또는 패 또는 n승 m패
    // Map<PlayerDto, ResultDto> -
    // Entry<List<PlayerDto>,List<ResultDTO>>

    // DealerResultDto / of / winCount, loseCount, pushCount
    // PlayerResultDto / of / name, result

    public void printGameResult(Map<Player, Result> gameResult) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("## 최종 승패\n");

        int playerWinCount = (int) gameResult.values().stream()
                .filter(result -> result == Result.PLAYER_WIN)
                .count();
        int dealerWinCount = (int) gameResult.values().stream()
                .filter(result -> result == Result.DEALER_WIN).
                count();
        int pushCount = (int) gameResult.values().stream()
                .filter(result -> result == Result.PUSH)
                .count();

        stringBuilder.append("딜러: " + dealerWinCount + "승 " + playerWinCount + "패 ");
        if (pushCount != 0) {
            stringBuilder.append(pushCount + "무");
        }
        stringBuilder.append("\n");
        for (Map.Entry<Player, Result> resultEntry : gameResult.entrySet()) {
            String a = resultEntry.getKey().getName().name() + ": ";
            if (resultEntry.getValue() == Result.PLAYER_WIN) {
                stringBuilder.append(a + "승\n");
            }
            if (resultEntry.getValue() == Result.DEALER_WIN) {
                stringBuilder.append(a + "패\n");
            }
            if (resultEntry.getValue() == Result.PUSH) {
                stringBuilder.append(a + "무\n");
            }
        }
        System.out.println(stringBuilder);
    }
}
