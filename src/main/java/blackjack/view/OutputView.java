package blackjack.view;

import blackjack.dto.WinningResult;
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

    public void printWinningResult(WinningResult winningResult) {
        System.out.println();
        System.out.println("## 최종 승패");
        printWinningResultOfDealer(winningResult.getWinCountOfDealer(), winningResult.getLoseCountOfDealer());
        printWinningResultOfPlayers(winningResult);
    }

    public void println() {
        System.out.println();
    }

    private void printWinningResultOfDealer(int winCountOfDealer, int loseCountOfDealer) {
        System.out.println("딜러: " + winCountOfDealer + "승 " + loseCountOfDealer + "패");
    }

    private void printWinningResultOfPlayers(WinningResult winningResult) {
        for (String playerName : winningResult.winningResult().keySet()) {
            System.out.println(playerName + ": " + convertToString(winningResult.get(playerName)));
        }
    }

    private String convertToString(boolean isWin) {
        if (isWin) {
            return "승";
        }
        return "패";
    }

    private String stringJoinWithComma(List<String> strings) {
        StringJoiner stringJoiner = new StringJoiner(",");
        strings.forEach(stringJoiner::add);

        return stringJoiner.toString();
    }

}
