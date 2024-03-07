package blackjack.view;

import blackjack.domain.Card;
import blackjack.domain.Dealer;
import blackjack.domain.Player;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    public static void printInitialHandOfEachPlayer(final Dealer dealer, final List<Player> players) {
        printInitialDistributionMessage(dealer, players);
        printDealerCard(dealer);
        players.forEach(OutputView::printPlayerCard);
    }

    private static void printInitialDistributionMessage(final Dealer dealer, final List<Player> players) {
        final String names = players.stream()
                .map(Player::getName)
                .collect(Collectors.joining(", "));

        final String initialDistributionMessage = String.format("%s와 %s에게 2장을 나누었습니다.", dealer.getName(), names);
        System.out.println(initialDistributionMessage);
    }

    private static void printDealerCard(final Dealer dealer) {
        final Card card = dealer.getFirstCard();

        System.out.println(dealer.getName() + ": " + card.getNumber() + card.getShape());
    }

    public static void printPlayerCard(final Player player) {
        // TODO: 문자열 분리 (상수 or 메서드)
        String cardInfo = player.getName() + "카드: " + player.getCards()
                .stream()
                // TODO: 이름 합치는 책임을 어디로 할 것인가 (도메인 vs 뷰)
                .map(card -> card.getNumber() + card.getShape())
                .collect(Collectors.joining(", "));
        System.out.println(player.getName() + ": " + cardInfo);
    }

    public static void printDealerDrawMessage(final Dealer dealer) {
        final String dealerDrawMessage = String.format("%s는 16이하라 한장의 카드를 더 받았습니다.", dealer.getName());
        System.out.println(dealerDrawMessage);
    }
}
