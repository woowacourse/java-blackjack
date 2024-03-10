package blackjack.view;

import blackjack.dto.ParticipantDto;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    public void printSetting(ParticipantDto dealer, List<ParticipantDto> players) {
        String playerNames = playerNameToString(players);
        System.out.printf("\n%s와 %s에게 2장을 나누었습니다.\n", dealer.playerName(), playerNames);
        printDealerCard(dealer);
        players.forEach(player -> {
            printCurrentCard(player);
            System.out.println();
        });
    }

    private String playerNameToString(List<ParticipantDto> players) {
        return players.stream()
                .map(ParticipantDto::playerName)
                .collect(Collectors.joining(", "));
    }

    private void printDealerCard(ParticipantDto dealer) {
        System.out.printf("%s: %s\n", dealer.playerName(), dealer.allHands().get(0));
    }

    public void printCurrentCard(ParticipantDto player) {
        System.out.printf("%s카드 : %s", player.playerName(), deckWithDelimiter(player.allHands()));
    }

    private String deckWithDelimiter(List<String> cards) {
        return String.join(", ", cards);
    }

    public void printScoreResult(ParticipantDto dealer, List<ParticipantDto> playerList) {
        printCurrentCard(dealer);
        printScore(dealer);
        playerList.forEach(player -> {
            printCurrentCard(player);
            printScore(player);
        });
        System.out.println();
    }

    private void printScore(ParticipantDto player) {
        System.out.printf(" - 결과: %d\n", player.score());
    }

    public void printDealerOneMoreCard() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
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

    private static void printVictory(Map<String, Boolean> victoryResult, String playerName) {
        if (victoryResult.get(playerName)) {
            System.out.println(playerName + ": 승");
            return;
        }
        System.out.println(playerName + ": 패");
    }

    public void printNewLine() {
        System.out.println();
    }
}
