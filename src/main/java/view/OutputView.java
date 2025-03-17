package view;

import domain.deck.Card;
import domain.gamer.Dealer;
import domain.gamer.Gamer;
import domain.gamer.Hand;
import domain.gamer.Player;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public final class OutputView {

    private static final ResourceBundle SHAPE_BUNDLE = ResourceBundle.getBundle("shape");
    private static final ResourceBundle RANK_BUNDLE = ResourceBundle.getBundle("rank");

    private OutputView() {
    }

    public static void printCardsInHandAtFirst(final List<Player> players, final Dealer dealer) {
        final String playersNames = players.stream()
                .skip(1)
                .map(Player::getDisplayName)
                .collect(Collectors.joining(", "));

        printInitialSettingMessage(dealer.getDisplayName(), playersNames);
        printCardsInHand(dealer, dealer.getInitialCards());
        players.forEach(player -> printCardsInHand(player, player.getInitialCards()));
    }

    public static void printInitialSettingMessage(final String dealerName, final String playersNames) {
        final String message = String.format("%s와 %s에게 2장을 나누었습니다.", dealerName, playersNames);
        print(message);
    }

    public static void printCardsInHand(final Gamer gamer, final List<Card> cards) {
        final List<String> cardGroup = cards.stream()
                .map(card -> RANK_BUNDLE.getString(card.getRank().name()) + SHAPE_BUNDLE.getString(
                        card.getShape().name()))
                .toList();
        final String message = String.format("%s카드: %s", gamer.getDisplayName(), String.join(", ", cardGroup));
        print(message);
    }

    public static void printCardsInHand(final Gamer gamer, final Hand hand) {
        final List<Card> cards = hand.getCards();
        final List<String> cardGroup = cards.stream()
                .map(card -> RANK_BUNDLE.getString(card.getRank().name()) + SHAPE_BUNDLE.getString(
                        card.getShape().name()))
                .toList();
        final String message = String.format("%s카드: %s", gamer.getDisplayName(), String.join(", ", cardGroup));
        print(message);
    }

    public static void printDealerHit() {
        print(String.format("딜러는 16이하라 한 장의 카드를 더 받았습니다.%n"));
    }

    public static void printCardsInHandWithResult(final Dealer dealer, final List<Player> players) {
        print(getMessage(dealer));
        for (final Player player : players) {
            print(getMessage(player));
        }
    }

    private static String getMessage(final Gamer gamer) {
        final List<Card> cards = gamer.getHand().getCards();
        final List<String> cardGroup = cards.stream()
                .map(card -> RANK_BUNDLE.getString(card.getRank().name()) + SHAPE_BUNDLE.getString(
                        card.getShape().name()))
                .toList();
        final int result = gamer.calculateSumOfRank();
        return String.format(
                "%s카드: %s - 결과: %d",
                gamer.getDisplayName(),
                String.join(", ", cardGroup),
                result
        );
    }

    public static void printProfitResult(final Map<String, Double> result, final double dealerProfit) {
        final NumberFormat formatter = NumberFormat.getInstance(Locale.KOREA);

        print(String.format("%n## 최종 수익"));
        print(String.format("딜러: %s원", formatter.format((int) -dealerProfit)));
        for (final Entry<String, Double> playerEntry : result.entrySet()) {
            final String playerNickname = playerEntry.getKey();
            final Double profit = playerEntry.getValue();
            print(String.format("%s: %s원", playerNickname, formatter.format(profit)));
        }
    }

    private static void print(final String message) {
        System.out.println(message);
    }
}
