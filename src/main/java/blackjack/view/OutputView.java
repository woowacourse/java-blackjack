package blackjack.view;

import blackjack.dto.ProfitResult;
import java.util.List;
import java.util.StringJoiner;

public class OutputView {

    public OutputView() {
    }

    public void printInitialSettingsDoneMessage(String dealerName, List<String> playersName) {
        System.out.println();
        System.out.println(dealerName + "와 " + stringJoinWithComma(playersName) + "에게 2장을 나누었습니다.");
    }

    public void printCardResults(String playerName, List<String> cards) {
        System.out.println(playerName + "카드: " + stringJoinWithComma(cards));
    }

    public void printCardResults(String playerName, List<String> cards, int score) {
        System.out.println(playerName + "카드: " + stringJoinWithComma(cards) + " - 결과: " + score);
    }

    public void printGetMoreCardsMessageForDealer(String dealerName) {
        System.out.println();
        System.out.println(dealerName + "는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printWinningResult(ProfitResult profitResult) {
        System.out.println();
        System.out.println("## 최종 승패");
        printWinningResultOfDealer(profitResult.getProfitOfDealer());
        printWinningResultOfPlayers(profitResult);
    }

    public void println() {
        System.out.println();
    }

    private void printWinningResultOfDealer(int profitOfDealer) {
        System.out.println("딜러: " + profitOfDealer);
    }

    private void printWinningResultOfPlayers(ProfitResult profitResult) {
        profitResult.profitResult().forEach((playerName, bettingAmount) ->
                System.out.println(playerName + ": " + bettingAmount)
        );
    }

    private String stringJoinWithComma(List<String> strings) {
        StringJoiner stringJoiner = new StringJoiner(",");
        strings.forEach(stringJoiner::add);

        return stringJoiner.toString();
    }

}
