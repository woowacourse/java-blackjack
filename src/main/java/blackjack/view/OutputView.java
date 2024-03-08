package blackjack.view;

import blackjack.dto.PlayerDto;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private static void printVictory(Map<String, Boolean> victoryResult, String playerName) {
        if (victoryResult.get(playerName)) {
            System.out.println(playerName + ": 승");
            return;
        }
        System.out.println(playerName + ": 패");
    }

    public void printSetting(PlayerDto dealer, List<PlayerDto> players) {
        String playerNames = playerNameToString(players);
        System.out.printf("\n%s와 %s에게 2장을 나누었습니다.\n", dealer.playerName(), playerNames);
        printDealerCard(dealer);
        players.forEach(player -> {
            printCurrentCard(player);
            System.out.println();
        });
    }

    private void printDealerCard(PlayerDto dealer) {
        System.out.printf("%s: %s\n", dealer.playerName(), dealer.deck().get(0));
    }

    public void printCurrentCard(PlayerDto player) {
        System.out.printf("%s카드 : %s", player.playerName(), deckWithDelimiter(player.deck()));
    }

    public void printScoreResult(PlayerDto dealer, List<PlayerDto> playerList) {
        printCurrentCard(dealer);
        printScore(dealer);
        playerList.forEach(player -> {
            printCurrentCard(player);
            printScore(player);
        });
        System.out.println();
    }

    public void printDealerOneMoreCard() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    private void printScore(PlayerDto player) {
        System.out.printf(" - 결과: %d\n", player.score());
    }

    public void printResult(Map<String, Boolean> victoryResult) {
        System.out.println("## 최종 승패");
        int dealerWinCount = (int) victoryResult.values().stream()
                .filter(isWin -> !isWin)
                .count();
        int dealerLoseCount = victoryResult.size() - dealerWinCount;
        System.out.printf("딜러: %d승 %d패\n", dealerWinCount, dealerLoseCount);
        victoryResult.keySet().forEach(playerName -> printVictory(victoryResult, playerName));
    }

    public void printNewLine() {
        System.out.println();
    }

    private String playerNameToString(List<PlayerDto> players) {
        return players.stream()
                .map(PlayerDto::playerName)
                .collect(Collectors.joining(", "));
    }

    private String deckWithDelimiter(List<String> cards) {
        return String.join(", ", cards);
    }
}
