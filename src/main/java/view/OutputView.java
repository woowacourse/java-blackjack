package view;

import domain.FinalResult;
import domain.deck.Card;
import domain.gamer.Dealer;
import domain.gamer.Gamer;
import domain.gamer.Nickname;
import domain.gamer.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public final class OutputView {

    private static final ResourceBundle FINAL_RESULT_BUNDLE = ResourceBundle.getBundle("finalResult");
    private static final ResourceBundle SHAPE_BUNDLE = ResourceBundle.getBundle("shape");
    private static final ResourceBundle RANK_BUNDLE = ResourceBundle.getBundle("rank");

    private OutputView() {
    }

    public static void printCardsInHandAtFirst(final Map<Nickname, List<Card>> cards) {
        final List<Nickname> nicknamesList = new ArrayList<>(cards.keySet());
        final Nickname dealerName = nicknamesList.getFirst();
        final String playersNames = nicknamesList.stream()
                .skip(1)
                .map(Nickname::getDisplayName)
                .collect(Collectors.joining(", "));

        printInitialSettingMessage(dealerName.getDisplayName(), playersNames);
        cards.forEach((nickname, cardGroup) -> {
            final String displayName = nickname.getDisplayName();
            printCardsInHand(displayName, cardGroup);
        });
    }

    public static void printInitialSettingMessage(final String dealerName, final String playersNames) {
        final String message = String.format("%s와 %s에게 2장을 나누었습니다.", dealerName, playersNames);
        print(message);
    }

    public static void printCardsInHand(final String name, final List<Card> cards) {
        final List<String> cardGroup = cards.stream()
                .map(card -> RANK_BUNDLE.getString(card.getRank().name()) + SHAPE_BUNDLE.getString(
                        card.getShape().name()))
                .toList();
        final String message = String.format("%s카드: %s", name, String.join(", ", cardGroup));
        print(message);
    }

    public static void printDealerHit(final String dealerName) {
        print(String.format("%s는 %d이하라 한 장의 카드를 더 받았습니다.", dealerName, 16));
    }

    public static void printBustMessage(final String name) {
        print(String.format("%s는 버스트입니다.", name));
    }

    public static void printCardsInHandWithResults(final Dealer dealer, final List<Player> players) {
        print(getMessage(dealer));
        for (final Player player : players) {
            print(getMessage(player));
        }
    }

    private static String getMessage(final Gamer gamer) {
        final List<Card> cards = gamer.getCards();
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

    public static void printFinalResults(final String dealerName,
                                         final Map<FinalResult, Integer> resultCounts,
                                         final Map<Player, FinalResult> playerResults
    ) {

        print("## 최종 승패");
        final Integer win = resultCounts.getOrDefault(FinalResult.WIN, 0);
        final Integer lose = resultCounts.getOrDefault(FinalResult.LOSE, 0);
        final Integer draw = resultCounts.getOrDefault(FinalResult.DRAW, 0);
        final String dealerMessage = String.format("%s: %d승 %d패 %d무", dealerName, lose, win, draw);
        print(dealerMessage);

        playerResults.forEach((player, finalResult) -> {
            final String playerMessage = String.format(
                    "%s: %s",
                    player.getDisplayName(),
                    FINAL_RESULT_BUNDLE.getString(finalResult.name())
            );
            print(playerMessage);
        });
    }

    private static void print(final String message) {
        System.out.println(message);
    }
}
