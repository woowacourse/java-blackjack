package client;

import card.Hand;
import participant.Player;
import participant.Players;
import participant.value.Score;
import result.AllPlayerResult;

public class OutputPrinter {
    private final OutputFormatter outputFormatter;

    public OutputPrinter(OutputFormatter outputFormatter) {
        this.outputFormatter = outputFormatter;
    }

    public void printInitialHand(Hand initialDealerHand, Players players) {
        String parsedPlayerNames = outputFormatter.formatPlayerNames(players.getNames());

        System.out.printf("\n딜러와 %s에게 2장을 나누었습니다.\n", parsedPlayerNames);
        System.out.printf("딜러카드: %s\n", outputFormatter.formatCards(initialDealerHand));
        players.getPlayers().forEach(player
                -> System.out.printf("%s카드: %s\n", player.getName(), outputFormatter.formatCards(player.getHand())));
    }

    public void printCurrentCard(Player player) {
        System.out.printf("\n%s카드: %s\n", player.getName(), outputFormatter.formatCards(player.getHand()));
    }

    public void printDealerDraw() {
        System.out.print("\n딜러는 16이하라 한장의 카드를 더 받았습니다.\n");
    }

    public void printDealerNoDraw() {
        System.out.print("\n딜러는 17이상이라 카드를 추가로 받지 않았습니다.\n");
    }

    public void printFinalDealerHand(Hand hand, Score score) {
        String parsedDealerCards = outputFormatter.formatCards(hand);
        int dealerCardsSum = score.score();
        System.out.printf("\n딜러카드: %s - 결과: %d\n", parsedDealerCards, dealerCardsSum);
    }

    public void printFinalPlayersHand(Players players) {
        players.getPlayers()
                .forEach(this::printFinalPlayerHand);
    }

    public void printFinalPlayerHand(Player player) {
        String parsedPlayerCards = outputFormatter.formatCards(player.getHand());
        int playerCardsSum = player.getScore().score();
        System.out.printf("%s카드: %s - 결과: %d\n", player.getName(), parsedPlayerCards, playerCardsSum);
    }

    public void printBustMessage() {
        System.out.println("\n카드의 합이 21을 초과하였습니다. 더이상 카드를 받을 수 없습니다.");
    }

    public void printAllPlayerResult(AllPlayerResult allPlayerResult) {
        System.out.println("\n## 최종 수익");
        System.out.printf("딜러: %d\n", allPlayerResult.calculateDealerProfit().price());
        allPlayerResult.playerResults()
                .forEach(playerResult -> {
                    System.out.printf("%s: %s\n", playerResult.getPlayerName(), playerResult.calculateProfit().price());
                });
    }
}
