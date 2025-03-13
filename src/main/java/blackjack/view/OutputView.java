package blackjack.view;

import blackjack.model.card.Card;
import blackjack.model.participant.Dealer;
import blackjack.model.participant.Player;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private static final String GAME_BANNER = """
            
            ================================
             Welcome to the Blackjack Game!
            ================================
                게임에 참여할 플레이어 이름은
                 2~5글자의 한글만 허용되며,
               다른 플레이어와 중복될 수 없습니다.
            ================================
            """;

    public void printStartBanner() {
        System.out.println(GAME_BANNER);
    }

    public void printInitialCards(Card dealerVisibleCard, List<Player> players) {
        System.out.printf("%n딜러와 %s에게 2장을 나누었습니다.%n", joinPlayerNamesWithComma(players));
        System.out.printf("딜러카드: %s%n", dealerVisibleCard.getDisplayLabel());
        for (Player player : players) {
            printPlayerHand(player);
        }
        System.out.println();
    }

    public void printPlayerHand(Player player) {
        System.out.printf("%s: %s%n", player.getName().value(), getHand(player));
    }

    private String joinPlayerNamesWithComma(List<Player> players) {
        return players.stream()
                .map(player -> player.getName().value())
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
            System.out.printf("%n딜러는 %d이하라 한장의 카드를 더 받았습니다.%n", Dealer.DEALER_HIT_THRESHOLD);
            return;
        }
        System.out.println();
    }

    public void printPlayerHandAndTotal(List<Player> players) {
        for (Player player : players) {
            System.out.printf("%s카드: %s - 결과: %d%n", player.getName().value(), getHand(player), player.getTotal());
        }
        System.out.println();
    }

    private String getHand(Player player) {
        return player.getHand()
                .stream()
                .map(Card::getDisplayLabel)
                .collect(Collectors.joining(", "));
    }
}
