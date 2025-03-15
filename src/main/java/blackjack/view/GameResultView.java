package blackjack.view;

import blackjack.model.card.Card;
import blackjack.model.participant.Player;
import java.util.List;
import java.util.stream.Collectors;

public class GameResultView {

    public void printDealerHandAndTotal(List<Card> hand, int total) {
        System.out.printf("딜러카드: %s - 결과: %d%n", joinCardWithComma(hand), total);
    }

    private String joinCardWithComma(List<Card> cards) {
        return cards.stream()
                .map(Card::getDisplayLabel)
                .collect(Collectors.joining(", "));
    }

    public void printPlayerHandAndTotal(List<Player> players) {
        for (Player player : players) {
            System.out.printf("%s카드: %s - 결과: %d%n", player.getName(), getHand(player),
                    player.getState().getHand().getTotal());
        }
        System.out.println();
    }

    private String getHand(Player player) {
        return player.getState().getHand().getCards()
                .stream()
                .map(Card::getDisplayLabel)
                .collect(Collectors.joining(", "));
    }

    public void printFinalProfitHeader() {
        System.out.println("## 최종 수익");
    }

    public void printDealerFinalProfit(int dealerProfit) {
        System.out.printf("딜러: %d%n", dealerProfit);
    }

    public void printPlayerFinalProfit(String playerName, int betAmount, int payout) {
        int finalProfit = payout - betAmount;
        System.out.printf("%s: %d%n", playerName, finalProfit);
    }
}
