package view;

import domain.Card;
import domain.Deck;
import domain.Player;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    public void printSetting(Player dealer, List<Player> playerList) {
        String playerNameToString = playerList.stream()
                .map(player -> player.getName().getName())
                .collect(Collectors.joining(", "));
        System.out.printf("%s와 %s에게 2장을 나누었습니다.\n", dealer.getName().getName(),playerNameToString);
        printDealerCard(dealer);
        playerList.forEach(player -> {
            printCurrentCard(player);
            System.out.println();
        });
    }

    private void printDealerCard(Player dealer) {
        Card dealerFirstCard = dealer.getDeck().getCards().get(0);
        String firstCardToString = dealerFirstCard.getRank().getRankName() + dealerFirstCard.getShape().getShapeName();
        System.out.printf("%s: %s\n", dealer.getName().getName(), firstCardToString);
    }
    public void printCurrentCard(Player player) {
        System.out.printf("%s카드 : %s", player.getName().getName(), deckToString(player));
    }

    public void printDealerOneMoreCard() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printResult(Player dealer, List<Player> playerList) {
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

    private static String deckToString(Player player) {
        Deck deck = player.getDeck();
        return deck.getCards()
                .stream()
                .map(card -> card.getRank().getRankName() + card.getShape().getShapeName())
                .collect(Collectors.joining(", "));
    }


    public void printNewLine() {
        System.out.println();
    }

    public void printVictory(Map<Player, Boolean> victoryResult) {
        System.out.println("## 최종 승패");
        System.out.println();
        int dealerWinCount = (int) victoryResult.values().stream()
                .filter(isWin -> !isWin)
                .count();
        System.out.printf("딜러: %d승 %d패\n", dealerWinCount, victoryResult.size() - dealerWinCount);
        victoryResult.keySet().forEach(player -> {
            if (victoryResult.get(player)) {
                System.out.println(player.getName().getName() + ": 승");
                return;
            }
            System.out.println(player.getName().getName() + ": 패");
        });
        
    }
}
