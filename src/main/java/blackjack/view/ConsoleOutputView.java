package blackjack.view;

import blackjack.card.Card;
import blackjack.gambler.Dealer;
import blackjack.gambler.Player;
import blackjack.gambler.Players;
import blackjack.constant.MatchResult;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ConsoleOutputView implements OutputView {

    @Override
    public void printInitialGameSettings(Players players, Dealer dealer) {
        String joinedPlayers = String.join(", ", players.getPlayerNames());
        System.out.println("\n딜러와 " + joinedPlayers + "에게 2장을 나누었습니다.");

        System.out.println("딜러카드: " + processCardsInfo(dealer.openInitialCards()));
        for (Player player : players.getPlayers()) {
            printPlayerCards(player);
        }
    }

    @Override
    public void printPlayerCards(Player player) {
        System.out.println(player.getNickname() + "카드: " + processCardsInfo(player.openCards()));
    }

    @Override
    public void printPlayerIsOverBust(Player player) {
        System.out.println(player.getNickname() + " BUST!!!");
    }

    @Override
    public void printDealerOneMoreCardMessage() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    @Override
    public void printGameSummary(Players players, Dealer dealer) {
        System.out.println("딜러카드: " + processCardsInfo(dealer.openCards()) + " - 결과: " + dealer.sumCardScores());
        for (Player player : players.getPlayers()) {
            System.out.println(player.getNickname() + "카드: " + processCardsInfo(player.openCards()) + " - 결과: "
                    + player.sumCardScores());
        }
    }

    @Override
    public void printGameResult(Map<Player, MatchResult> playerResults) {
        System.out.println();
        System.out.println("## 최종 승패");
        printDealerResult(playerResults);
        printPlayerResults(playerResults);
    }

    private void printDealerResult(Map<Player, MatchResult> playerMatchResultMap) {
        Map<MatchResult, Integer> matchResultCounts = getMatchResultCounts();
        for (MatchResult matchResult : playerMatchResultMap.values()) {
            matchResultCounts.put(matchResult, matchResultCounts.get(matchResult) + 1);
        }
        System.out.println("딜러: " + formatDealerWin(matchResultCounts.get(MatchResult.LOSE))
                + formatDealerDraw(matchResultCounts.get(MatchResult.DRAW))
                + formatDealerLose(matchResultCounts.get(MatchResult.WIN)));
    }

    private Map<MatchResult, Integer> getMatchResultCounts() {
        Map<MatchResult, Integer> matchResultCounts = new EnumMap<>(MatchResult.class);
        matchResultCounts.put(MatchResult.WIN, 0);
        matchResultCounts.put(MatchResult.DRAW, 0);
        matchResultCounts.put(MatchResult.LOSE, 0);
        return matchResultCounts;
    }

    private void printPlayerResults(Map<Player, MatchResult> playerResults) {
        for (Entry<Player, MatchResult> entry : playerResults.entrySet()) {
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
        return card.getRank().getName() + card.getSuit().getName();
    }
}
