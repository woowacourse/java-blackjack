package io;

import bet.BettingCenter;
import game.Card;
import participant.Dealer;
import participant.Player;
import participant.Players;
import constant.WinningResult;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ConsoleOutput {

    public void printInitialGameSettings(Players players, Dealer dealer) {
        String joinedPlayers = String.join(", ", players.getPlayerNames());
        System.out.println("\n딜러와 " + joinedPlayers + "에게 2장을 나누었습니다.");

        System.out.println("딜러카드: " + processCardInfo(dealer.openOneCard()));
        for (Player player : players.getPlayers()) {
            printPlayerCards(player);
        }
    }

    public void printPlayerCards(Player player) {
        System.out.println(player.getNickname() + "카드: " + processCardsInfo(player.openCards()));
    }

    public void printDealerHitMessage() {
        System.out.println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printGameResults(Players players, Dealer dealer) {
        System.out.println("\n딜러카드: " + processCardsInfo(dealer.openCards()) + " - 결과: " + dealer.sumCardNumbers());
        for (Player player : players.getPlayers()) {
            System.out.println(player.getNickname() + "카드: " + processCardsInfo(player.openCards()) + " - 결과: "
                    + player.sumCardNumbers());
        }
    }

    public void printWinningResults(Map<Player, WinningResult> playerResults) {
        System.out.println();
        System.out.println("## 최종 승패");
        printDealerResult(playerResults);
        printPlayerResults(playerResults);
    }

    private void printDealerResult(Map<Player, WinningResult> playerWinDrawLoseMap) {
        Map<WinningResult, Integer> winDrawLoseCounts = getGameResultCounts();
        for (WinningResult winningResult : playerWinDrawLoseMap.values()) {
            winDrawLoseCounts.put(winningResult, winDrawLoseCounts.get(winningResult) + 1);
        }
        System.out.println("딜러: " + formatDealerWin(winDrawLoseCounts.get(WinningResult.LOSE))
                + formatDealerDraw(winDrawLoseCounts.get(WinningResult.DRAW))
                + formatDealerLose(winDrawLoseCounts.get(WinningResult.WIN)));
    }

    private Map<WinningResult, Integer> getGameResultCounts() {
        Map<WinningResult, Integer> resultCounts = new EnumMap<>(WinningResult.class);
        resultCounts.put(WinningResult.WIN, 0);
        resultCounts.put(WinningResult.DRAW, 0);
        resultCounts.put(WinningResult.LOSE, 0);
        return resultCounts;
    }

    private void printPlayerResults(Map<Player, WinningResult> playerResults) {
        for (Entry<Player, WinningResult> entry : playerResults.entrySet()) {
            System.out.println(entry.getKey().getNickname() + ": " + entry.getValue().getProfitRate());
        }
    }

    private String formatDealerWin(int dealerWinCount) {
        if (dealerWinCount > 0) {
            return dealerWinCount + "승 ";
        }
        return "";
    }

    private String formatDealerDraw(int dealerDrawCount) {
        if (dealerDrawCount > 0) {
            return dealerDrawCount + "무 ";
        }
        return "";
    }

    private String formatDealerLose(int dealerLoseCount) {
        if (dealerLoseCount > 0) {
            return dealerLoseCount + "패 ";
        }
        return "";
    }

    private String processCardsInfo(List<Card> cards) {
        return String.join(", ", cards.stream()
                .map(this::processCardInfo)
                .toList());
    }

    private String processCardInfo(Card card) {
        return card.getNumber().getName() + card.getEmblem().getName();
    }

    public void printFinalProfit(BettingCenter bettingCenter, Dealer dealer) {
        System.out.println("\n## 최종 수익");
        System.out.println("딜러: " + bettingCenter.calculateDealerProfit(dealer));
        Map<Player, Integer> deriveBetResults = bettingCenter.deriveBettingResults(dealer);
        for (Player player : deriveBetResults.keySet()) {
            System.out.println(player.getNickname() + ": " + deriveBetResults.get(player));
        }
    }
}
