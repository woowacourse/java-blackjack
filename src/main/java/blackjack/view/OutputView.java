package blackjack.view;

import static blackjack.model.constants.RuleConstants.DEALER_HIT_THRESHOLD;

import blackjack.model.betting.Profit;
import blackjack.model.card.Card;
import blackjack.model.participant.Player;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class OutputView {

    public void printInitialCards(Card dealerVisibleCard, List<Player> players) {
        System.out.printf("%n딜러와 %s에게 2장을 나누었습니다.%n", joinPlayerNamesWithComma(players));
        System.out.printf("딜러카드: %s%n", dealerVisibleCard.getDisplayLabel());
        for (Player player : players) {
            printPlayerHand(player);
        }
        System.out.println();
    }

    public void printPlayerHand(Player player) {
        System.out.printf("%s: %s%n", player.getName(), getHand(player));
    }

    private String joinPlayerNamesWithComma(List<Player> players) {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.joining(", "));
    }

    public void printDealerHandAndTotal(List<Card> hand, int total) {
        System.out.printf("딜러카드: %s - 결과: %d%n", joinCardWithComma(hand), total);
    }

    private String joinCardWithComma(List<Card> cards) {
        return cards.stream()
                .map(Card::getDisplayLabel)
                .collect(Collectors.joining(", "));
    }

    public void printDealerHit(boolean isDealerHit) {
        if (isDealerHit) {
            System.out.printf("%n딜러는 %d이하라 한장의 카드를 더 받았습니다.%n", DEALER_HIT_THRESHOLD);
            return;
        }
        System.out.println();
    }

    public void printPlayerHandAndTotal(List<Player> players) {
        for (Player player : players) {
            System.out.printf("%s카드: %s - 결과: %d%n", player.getName(), getHand(player), player.calculateHandTotal());
        }
        System.out.println();
    }

    private String getHand(Player player) {
        return player.getHand()
                .stream()
                .map(Card::getDisplayLabel)
                .collect(Collectors.joining(", "));
    }

    public void printProfit(Profit dealerProfit, Map<Player, Profit> playersProfit) {
        System.out.println("## 최종 수익");
        System.out.println("딜러: " + dealerProfit.getProfit());
        for (Entry<Player, Profit> results : playersProfit.entrySet()) {
            System.out.printf("%s: %d%n", results.getKey().getName(), results.getValue().getProfit());
        }
    }
}
