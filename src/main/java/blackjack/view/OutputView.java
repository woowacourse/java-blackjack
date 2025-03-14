package blackjack.view;

import blackjack.model.blackjack_player.dealer.Dealer;
import blackjack.model.blackjack_player.player.Player;
import blackjack.model.card.BlackJackCards;
import java.util.List;
import java.util.stream.Collectors;

public final class OutputView {

    public void printDealInitialCardsResult(final Dealer dealer, final List<Player> players) {
        String userNames = players.stream()
                .map(Player::getName)
                .collect(Collectors.joining(", "));
        System.out.println();
        System.out.println("딜러와 " + userNames + "에게 2장을 나누었습니다.");
        System.out.println("딜러카드: " + formatCards(dealer.openCards()));
        players.forEach(this::printPlayerCards);
        System.out.println();
    }

    public void printPlayerCards(final Player player) {
        System.out.println(
                player.getName() + "카드: " + formatCards(player.getCards()));
    }

    private String formatCards(final BlackJackCards blackJackCards) {
        return blackJackCards.getValues()
                .stream()
                .map(card -> card.cardNumber().getName() + card.cardType().getName())
                .collect(Collectors.joining(", "));
    }

    public void printDealerDrawnMoreCards(final boolean isDrawn) {
        System.out.println();
        if (isDrawn) {
            System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다." + System.lineSeparator());
            return;
        }
        System.out.println("딜러는 한장의 카드를 더 받지 않았습니다." + System.lineSeparator());
    }

    public void printGameResult(final List<Player> players) {
        System.out.println("## 최종 수익");
        printDealerResult(players);
        printUsersResults(players);
    }

    private void printDealerResult(final List<Player> players) {
        int dealerProfit = players.stream()
                .mapToInt(Player::getProfit)
                .sum();
        System.out.println("딜러: " + dealerProfit);
    }

    private void printUsersResults(final List<Player> players) {
        players.forEach(
                player -> System.out.println(player.getName() + ": " + player.getProfit()));
    }

    public void printOptimalPoints(final Dealer dealer, final List<Player> players) {
        System.out.println(
                "딜러카드: " + formatCards(dealer.getAllCards()) + " - 결과: "
                        + dealer.getOptimalPoint());
        players.forEach(player -> System.out.println(
                player.getName() + "카드: " + formatCards(player.getCards()) + " - 결과: "
                        + player.getOptimalPoint()));
        System.out.println();
    }
}
