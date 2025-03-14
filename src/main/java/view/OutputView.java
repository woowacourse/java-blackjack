package view;

import domain.deck.Card;
import domain.gamer.Dealer;
import domain.gamer.Gamer;
import domain.gamer.Player;
import domain.gamer.Players;
import java.util.List;
import java.util.Map;

public final class OutputView {

    private OutputView() {
    }

    public static void printInitialSettingMessage(final String dealerName,
                                                  final List<String> playerNames,
                                                  final int cardAmount) {
        final String message = String.format(
                "%s와 %s에게 %d장을 나누었습니다.",
                dealerName,
                String.join(", ", playerNames),
                cardAmount
        );
        print(message);
    }

    public static void printCardsInHand(final String name, final List<Card> cards) {

        final List<String> cardGroup = cards.stream()
                .map(card -> card.getDisplayName() + card.getShape().getTitle())
                .toList();
        final String message = String.format("%s카드: %s", name, String.join(", ", cardGroup));
        print(message);
    }

    public static void printDealerHit(final int threshold, final String dealerName) {
        print(String.format("%s는 %d이하라 한 장의 카드를 더 받았습니다.", dealerName, threshold));
    }

    public static void printBustMessage(final String name) {
        print(String.format("%s는 버스트입니다.", name));
    }

    public static void printCardsInHandWithResults(final Dealer dealer, final Players players) {
        print(getMessage(dealer));
        for (final Player player : players.getPlayers()) {
            print(getMessage(player));
        }
    }

    private static String getMessage(final Gamer gamer) {
        final List<Card> cards = gamer.getCards();
        final List<String> cardGroup = cards.stream()
                .map(card -> card.getDisplayName() + card.getShape().getTitle())
                .toList();
        final int result = gamer.calculateSumOfRank();
        return String.format(
                "%s카드: %s - 결과: %d",
                gamer.getDisplayName(),
                String.join(", ", cardGroup),
                result
        );
    }

    public static void printProfitResults(final Map<Gamer, Integer> profitResults) {
        print("## 최종 수익");
        for (Map.Entry<Gamer, Integer> entry : profitResults.entrySet()) {
            print(String.format(entry.getKey().getDisplayName() + ": " + entry.getValue()));
        }
    }


    public static void printErrorMessage(final String message) {
        print(message);
    }

    private static void print(final String message) {
        System.out.println(message);
    }
}
