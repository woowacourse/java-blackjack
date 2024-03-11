package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.domain.cardgame.CardGameResult;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static blackjack.domain.card.CardNumber.*;
import static blackjack.domain.card.CardShape.CLOVER;
import static blackjack.domain.card.CardShape.DIAMOND;
import static blackjack.domain.card.CardShape.HEART;
import static blackjack.domain.card.CardShape.SPADE;
import static blackjack.domain.cardgame.WinningStatus.LOSE;
import static blackjack.domain.cardgame.WinningStatus.WIN;

public class OutputView {
    private static final Map<CardShape, String> CARD_SHAPE_NAME_MAP = Map.of(
            SPADE, "스페이드",
            HEART, "하트",
            DIAMOND, "다이아몬드",
            CLOVER, "클로버"
    );

    private static final Map<CardNumber, String> CARD_NUMBER_NAME_MAP = Map.ofEntries(
            Map.entry(ACE, "A"), Map.entry(TWO, "2"),
            Map.entry(THREE, "3"), Map.entry(FOUR, "4"),
            Map.entry(FIVE, "5"), Map.entry(SIX, "6"),
            Map.entry(SEVEN, "7"), Map.entry(EIGHT, "8"),
            Map.entry(NINE, "9"), Map.entry(TEN, "10"),
            Map.entry(JACK, "J"), Map.entry(QUEEN, "Q"),
            Map.entry(KING, "K")
    );

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
        cardGameResult.totalResult()
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
        return CARD_NUMBER_NAME_MAP.get(card.getNumber()) + CARD_SHAPE_NAME_MAP.get(card.getShape());
    }

    private static void printLineSeparator() {
        System.out.println();
    }
}
