package view;

import domain.Card;
import domain.Deck;
import domain.Player;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    public void printSetting(Player dealer, List<Player> players) {
        String playerNames = playerNameToString(players);
        System.out.printf("%s와 %s에게 2장을 나누었습니다.\n", dealer.getName().getName(),playerNames);
        printDealerCard(dealer);
        players.forEach(player -> {
            printCurrentCard(player);
            System.out.println();
        });
    }

    private void printDealerCard(Player dealer) {
        Card dealerFirstCard = dealer.getDeck().getCards().get(0);
        String card = cardToString(dealerFirstCard);
        System.out.printf("%s: %s\n", dealer.getName().getName(), card);
    }

    public void printCurrentCard(Player player) {
        System.out.printf("%s카드 : %s", player.getName().getName(), deckToString(player.getDeck()));
    }

    public void printScoreResult(Player dealer, List<Player> playerList) {
        printCurrentCard(dealer);
        printScore(dealer);
        playerList.forEach(player -> {
            printCurrentCard(player);
            printScore(player);
        });
    }

    private void printScore(Player player) {
        System.out.printf(" - 결과: %d\n", player.getDeck().calculateTotalScore());
    }

    public void printDealerOneMoreCard() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printResult(Map<Player, Boolean> victoryResult) {
        System.out.println("## 최종 승패");
        System.out.println();
        int dealerWinCount = (int) victoryResult.values().stream()
                .filter(isWin -> !isWin)
                .count();
        int dealerLoseCount = victoryResult.size() - dealerWinCount;
        System.out.printf("딜러: %d승 %d패\n", dealerWinCount, dealerLoseCount);
        victoryResult.keySet().forEach(player -> printVictory(victoryResult, player));
    }

    private static void printVictory(Map<Player, Boolean> victoryResult, Player player) {
        if (victoryResult.get(player)) {
            System.out.println(player.getName().getName() + ": 승");
            return;
        }
        System.out.println(player.getName().getName() + ": 패");
    }

    public void printNewLine() {
        System.out.println();
    }

    private String playerNameToString(List<Player> players) {
        return players.stream()
                .map(player -> player.getName().getName())
                .collect(Collectors.joining(", "));
    }

    private String deckToString(Deck deck) {
        return deck.getCards()
                .stream()
                .map(this::cardToString)
                .collect(Collectors.joining(", "));
    }

    private String cardToString(Card card) {
        return card.getRank().getRankName() + card.getShape().getShapeName();
    }
}
