package blackjack.view;

import blackjack.card.Card;
import blackjack.game.betting.BetAmount;
import blackjack.user.Dealer;
import blackjack.user.Player;
import blackjack.user.PlayerName;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    public void printInitDistributionMessage(final List<PlayerName> playerNames) {
        System.out.println();
        List<String> names = playerNames.stream()
            .map(PlayerName::getText)
            .toList();
        System.out.printf("딜러와 %s에게 2장을 나누었습니다.%n", String.join(", ", names));
    }

    public void printDealerCardResult(final Dealer dealer) {
        String cardResult = parseCardToString(dealer.openInitialCards());
        System.out.printf("딜러카드: %s%n", cardResult);
    }

    public void printPlayersCardResult(final List<Player> players) {
        for(Player player : players) {
            printPlayerCardResult(player);
        }
    }

    public void printPlayerCardResult(final Player player) {
        String cardResult = parseCardToString(player.openInitialCards());
        System.out.printf("%s카드: %s%n", player.getName().getText(), cardResult);
    }

    public void printAddExtraCardToDealer() {
        System.out.println();
        System.out.println("딜러는 16이하라 한장의 카드를 더 받습니다.");
    }

    public void printDealerFinalCardResult(final Dealer dealer) {
        System.out.println();
        String cardResult = parseCardToString(dealer.getCards().openCards());
        System.out.printf("딜러카드: %s - 결과: %d%n", cardResult,
            dealer.getCards().calculateDenominations());
    }

    public void printPlayersFinalCardResult(final List<Player> players) {
        for(Player player : players) {
            String cardResult = parseCardToString(player.getCards().openCards());
            System.out.printf("%s카드: %s - 결과 %d%n", player.getName().getText(), cardResult,
                player.getCards().calculateDenominations());
        }
    }

    public void printProfitResultTitle() {
        System.out.println();
        System.out.println("## 최종 수익");
    }

    public void printDealerResult(final int dealerProfitResult) {
        System.out.printf("딜러: %,d%n", dealerProfitResult);
    }

    public void printPlayerResult(final Map<PlayerName, BetAmount> playersProfit) {
        playersProfit.forEach((name, betAmount) ->
            System.out.printf("%s: %,d%n", name.getText(), betAmount.getProfit())
        );
    }

    private String parseCardToString(final List<Card> cards) {
        return cards
            .stream()
            .map(card -> card.denomination().getText() + card.suit().getText())
            .collect(Collectors.joining(", "));
    }
}
