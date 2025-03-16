package view;

import blackjack.card.Card;
import blackjack.gamer.Dealer;
import blackjack.gamer.Gamer;
import blackjack.gamer.Player;
import blackjack.result.ProfitResult;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final String NEXT_LINE = System.lineSeparator();

    public void printInitialCards(Dealer dealer, List<Player> players) {
        String playerNames = getPlayerNames(players);
        System.out.printf(NEXT_LINE + "%s와 %s에게 2장을 나누었습니다.%s", "딜러", playerNames, NEXT_LINE);

        System.out.printf("%s카드: %s%n", dealer.getNickname(),
                formatSingleCard(dealer.getHand().getCards().getFirst()));

        players.forEach(this::printCards);
        System.out.println();
    }

    private String getPlayerNames(List<Player> players) {
        return players.stream()
                .map(Player::getNickname)
                .collect(Collectors.joining(", "));
    }

    public void printCards(Player player) {
        cardFormat(player);
        System.out.println();
    }

    public void printDealerHitSuccess() {
        System.out.println(NEXT_LINE + "딜러는 16이하라 한장의 카드를 더 받았습니다." + NEXT_LINE);
    }

    public void printCardResult(Dealer dealer, List<Player> players) {
        System.out.println();
        cardFormat(dealer);
        printScore(dealer);

        for (Player player : players) {
            cardFormat(player);
            printScore(player);
        }
    }

    private void cardFormat(Gamer player) {
        String cards = player.getHand().getCards().stream()
                .map(this::formatSingleCard)
                .collect(Collectors.joining(", "));
        System.out.printf("%s카드: %s", player.getNickname(), cards);
    }

    private String formatSingleCard(Card card) {
        return String.format("%s%s", card.getCardRank().getName(), card.getCardSymbol().getName());
    }

    private void printScore(Gamer gamer) {
        System.out.printf(" - 결과: %d%n", gamer.getScore());
    }

    public void printProfitResult(ProfitResult profitResult) {
        System.out.println("\n## 최종 수익");
        System.out.printf("딜러: %d원\n", profitResult.getDealerProfit());

        profitResult.getPlayerProfits().forEach(
                (player, profit) -> System.out.printf("%s: %d원\n", player.getNickname(), profit)
        );
    }
}
