package blackjack.view;

import blackjack.model.card.BlackJackCards;
import blackjack.model.player.BlackJackPlayer;
import blackjack.model.player.Dealer;
import blackjack.model.player.Player;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    public void printDealInitialCardsResult(final Dealer dealer, final List<Player> players) {
        String userNames = players.stream()
                .map(BlackJackPlayer::getName)
                .collect(Collectors.joining(", "));
        System.out.println();
        System.out.println(dealer.getName() + "와 " + userNames + "에게 2장을 나누었습니다.");
        printPlayerCards(dealer);
        players.forEach(this::printPlayerCards);
        System.out.println();
    }

    public void printPlayerCards(final BlackJackPlayer blackJackPlayer) {
        System.out.println(blackJackPlayer.getName() + "카드: " + formatCards(blackJackPlayer.openInitialCards()));
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

    public void printGameResult(final Dealer dealer, final List<Player> players) {
        System.out.println("## 최종 수익");
        printDealerResult(dealer, players);
        printUsersResults(players);
    }

    private void printDealerResult(final Dealer dealer, final List<Player> players) {
        System.out.println(dealer.getName() + ": " + dealer.getProfit(players));
    }

    private void printUsersResults(final List<Player> players) {
        players.forEach(player -> System.out.println(player.getName() + ": " + player.getProfit()));
    }

    public void printOptimalPoints(final Dealer dealer, final List<Player> players) {
        System.out.println(
                dealer.getName() + "카드: " + formatCards(dealer.openAllCards()) + " - 결과: "
                        + dealer.calculateOptimalPoint());
        players.forEach(user -> System.out.println(
                user.getName() + "카드: " + formatCards(user.openAllCards()) + " - 결과: " + user.calculateOptimalPoint()));
        System.out.println();
    }
}
