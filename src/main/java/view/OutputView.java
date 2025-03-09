package view;

import domain.Card;
import domain.Dealer;
import domain.Player;
import domain.Players;
import domain.constant.GameResult;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class OutputView {

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

    public void printPlayerIsOverBust(Player player) {
        System.out.println(player.getNickname() + " BUST!!!");
    }

    public void printDealerOneMoreCardMessage() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printGameSummary(Players players, Dealer dealer) {
        System.out.println("딜러카드: " + processCardsInfo(dealer.openCards()) + " - 결과: " + dealer.sumCardNumbers());
        for (Player player : players.getPlayers()) {
            System.out.println(player.getNickname() + "카드: " + processCardsInfo(player.openCards()) + " - 결과: "
                    + player.sumCardNumbers());
        }
    }

    public void printGameResult(Map<Player, GameResult> playerResults) {
        System.out.println();
        System.out.println("## 최종 승패");
        printDealerResult(playerResults);
        printPlayerResults(playerResults);
    }

    private void printDealerResult(Map<Player, GameResult> playerWinDrawLoseMap) {
        Map<GameResult, Integer> winDrawLoseCounts = getWinDrawLoseCounts();
        for (GameResult gameResult : playerWinDrawLoseMap.values()) {
            winDrawLoseCounts.put(gameResult, winDrawLoseCounts.get(gameResult) + 1);
        }
        System.out.println("딜러: " + formatDealerWin(winDrawLoseCounts.get(GameResult.LOSE))
                + formatDealerDraw(winDrawLoseCounts.get(GameResult.DRAW))
                + formatDealerLose(winDrawLoseCounts.get(GameResult.WIN)));
    }

    private Map<GameResult, Integer> getWinDrawLoseCounts() {
        Map<GameResult, Integer> winDrawLoseCounts = new EnumMap<>(GameResult.class);
        winDrawLoseCounts.put(GameResult.WIN, 0);
        winDrawLoseCounts.put(GameResult.DRAW, 0);
        winDrawLoseCounts.put(GameResult.LOSE, 0);
        return winDrawLoseCounts;
    }

    private void printPlayerResults(Map<Player, GameResult> playerResults) {
        for (Entry<Player, GameResult> entry : playerResults.entrySet()) {
            System.out.println(entry.getKey().getNickname() + ": " + entry.getValue().getMessage());
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
}
