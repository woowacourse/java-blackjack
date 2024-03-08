package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.cardgame.CardGameResult;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;

import java.util.List;
import java.util.stream.Collectors;

import static blackjack.domain.cardgame.WinningStatus.LOSE;
import static blackjack.domain.cardgame.WinningStatus.WIN;

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

        printLineSeparator();
        final String initialDistributionMessage = String.format("%s와 %s에게 2장을 나누었습니다.", dealer.getName(), names);
        System.out.println(initialDistributionMessage);
    }

    private static void printDealerCard(final Dealer dealer) {
        final Card card = dealer.getFirstCard();
        System.out.println(dealer.getName() + ": " + getCardInfo(card));
    }

    public static void printPlayerCard(final Player player) {
        String playerCardInfo = getPlayerCardInfo(player);
        System.out.println(playerCardInfo);
    }

    // TODO: Dealer 타입이 아닌 Name을 받도록 수정
    // TODO: 16이라는 조건을 어디서 관리해야 하는가
    public static void printDealerHitMessage(final Dealer dealer) {
        final String dealerHitMessage = String.format("%s는 16이하라 한장의 카드를 더 받았습니다.", dealer.getName());
        System.out.println(dealerHitMessage);
        printLineSeparator();
    }
    public static void printPlayerCardWithScore(final Player player) {
        String playerCardInfo = getPlayerCardInfo(player);
        System.out.println(playerCardInfo + " - 결과: " + player.getScore());
    }

    public static void printResult(final CardGameResult cardGameResult) {
        printResultOfDealer(cardGameResult);
        printResultOfEachPlayer(cardGameResult);
    }

    private static void printResultOfDealer(final CardGameResult cardGameResult) {
        printLineSeparator();
        System.out.println("## 최종 승패");
        System.out.println("딜러: " +
                cardGameResult.getDealerWinCount() + WIN.getValue() + " " +
                cardGameResult.getDealerLoseCount() + LOSE.getValue());
    }

    private static void printResultOfEachPlayer(final CardGameResult cardGameResult) {
        cardGameResult.getTotalResult()
                .entrySet()
                .stream()
                .map(result -> result.getKey().getName() + ": " + result.getValue().getValue())
                .forEach(System.out::println);
    }

    private static String getPlayerCardInfo(final Player player) {
        return player.getName() + "카드: " +
                player.getCards()
                        .stream()
                        .map(OutputView::getCardInfo)
                        .collect(Collectors.joining(", "));
    }

    private static String getCardInfo(final Card card) {
        return card.getNumber() + card.getShape();
    }

    private static void printLineSeparator() {
        System.out.println();
    }
}
