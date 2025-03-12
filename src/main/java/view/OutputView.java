package view;

import domain.card.Card;
import domain.card.CardDeck;
import domain.game.Dealer;
import domain.game.Player;
import domain.game.Players;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    public void printInitialGame(Card dealerCard, List<Player> players) {
        String playerNames = formatPlayerNames(players);
        System.out.printf("%n딜러와 %s에게 " + CardDeck.DRAW_COUNT_WHEN_START + "장을 나누었습니다.%n", playerNames);

        System.out.printf("딜러카드: %s%n", dealerCard.formatSingleCard());
        for (Player player : players) {
            printPlayerCard(player);
        }
        System.out.println();
    }

    private String formatPlayerNames(List<Player> players) {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.joining(", "));
    }

    public void printPlayerCard(Player player) {
        System.out.printf("%s카드: %s%n", player.getName(), formatCard(player.getCards()));
    }

    public void printGameResult(Dealer dealer, Players players) {
        String dealerResult = formatCard(dealer.getCards());
        System.out.printf("%n딜러카드: %s - 결과: %d%n", dealerResult, dealer.calculateTotalCardNumber());

        for (Player player : players.getPlayers()) {
            String playerResult = formatCard(player.getCards());
            System.out.printf("%s카드: %s - 결과: %d%n", player.getName(), playerResult, player.calculateTotalCardNumber());
        }
        System.out.println();
    }

    private String formatCard(List<Card> cards) {
        return cards.stream()
                .map(Card::formatSingleCard)
                .collect(Collectors.joining(","));
    }

    public void printDealerDrawMessage() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printBettingAmount(List<Player> players, Dealer dealer) {
        System.out.println("## 최종 수익");
        System.out.printf("딜러: %d%n", dealer.getTotalProfit());
        players.forEach(player -> System.out.printf("%s: %d%n", player.getName(), player.getBettingAmount()));
    }
}
