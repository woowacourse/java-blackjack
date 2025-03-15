package blackjack.view;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import blackjack.model.card.Cards;
import blackjack.model.player.Player;

public class OutputView {

    public void printDealInitialCardsResult(final List<Player> players, final List<Cards> openCards) {
        String userNames = players.stream()
                .skip(1L)
                .map(Player::getName)
                .collect(Collectors.joining(", "));
        String dealerName = players.getFirst().getName();
        System.out.println();
        System.out.printf("%s와 %s에게 2장을 나누었습니다.%n", dealerName, userNames);
        IntStream.range(0, players.size())
                .forEachOrdered(index -> printPlayerCards(players.get(index), openCards.get(index)));
        System.out.println();
    }

    public void printPlayerCards(final Player player, final Cards cards) {
        System.out.printf("%s카드: %s%n", player.getName(), formatCards(cards));
    }

    private String formatCards(final Cards cards) {
        return cards.getValues()
                .stream()
                .map(card -> card.cardNumber().getName() + card.cardType().getName())
                .collect(Collectors.joining(", "));
    }

    public void printDealerDrawnMoreCards(boolean isDrawn) {
        System.out.println();
        if (isDrawn) {
            System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다." + System.lineSeparator());
            return;
        }
        System.out.println("딜러는 한장의 카드를 더 받지 않았습니다." + System.lineSeparator());
    }

    public void printOptimalPoints(final Map<Player, Integer> optimalPoints) {
        optimalPoints.forEach((player, value) -> System.out.printf(
                "%s카드: %s - 결과: %d%n", player.getName(), formatCards(player.getCards()), value
        ));
        System.out.println();
    }

    public void printFinalWinnings(final Map<Player, BigDecimal> playerWinnings) {
        System.out.println("## 최종 수익");
        NumberFormat numberFormat = DecimalFormat.getInstance(Locale.KOREA);
        playerWinnings.entrySet()
                .stream()
                .map(entry -> entry.getKey().getName() + ": " + numberFormat.format(entry.getValue()))
                .forEach(System.out::println);
    }

}
