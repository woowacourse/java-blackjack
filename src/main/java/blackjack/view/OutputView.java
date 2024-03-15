package blackjack.view;

import blackjack.dto.BettingMoneyDto;
import blackjack.dto.GamerDto;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    public void printMoneyResult(BettingMoneyDto bettingMoneyResult) {
        System.out.println("## 최종 수익");
        System.out.println(bettingMoneyResult.toString());
    }

    public void printSetting(GamerDto dealer, List<GamerDto> players) {
        String playerNames = playerNameToString(players);
        System.out.printf("\n%s와 %s에게 2장을 나누었습니다.\n", dealer.GamerName(), playerNames);
        printDealerCard(dealer);
        players.forEach(player -> {
            printCurrentCard(player);
            System.out.println();
        });
    }

    private void printDealerCard(GamerDto dealer) {
        System.out.printf("%s: %s\n", dealer.GamerName(), dealer.hand().get(0));
    }

    public void printCurrentCard(GamerDto player) {
        System.out.printf("%s카드 : %s", player.GamerName(), deckWithDelimiter(player.hand()));
    }

    public void printScoreResult(GamerDto dealer, List<GamerDto> playerList) {
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

    private void printScore(GamerDto player) {
        System.out.printf(" - 결과: %d\n", player.score());
    }

    public void printNewLine() {
        System.out.println();
    }

    private String playerNameToString(List<GamerDto> players) {
        return players.stream()
                .map(GamerDto::GamerName)
                .collect(Collectors.joining(", "));
    }

    private String deckWithDelimiter(List<String> cards) {
        return String.join(", ", cards);
    }
}
