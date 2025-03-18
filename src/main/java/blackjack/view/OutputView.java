package blackjack.view;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import blackjack.model.card.Cards;
import blackjack.model.player.Player;
import blackjack.model.player.Players;

public class OutputView {

    public void printDealInitialCardsResult(final Players players) {
        String userNames = players.getUsers().stream()
                .skip(1L)
                .map(Player::getName)
                .collect(Collectors.joining(", "));
        String dealerName = players.getDealer().getName();
        System.out.println();
        System.out.printf("%s와 %s에게 2장을 나누었습니다.%n", dealerName, userNames);
        printPlayerCards(players.getDealer());
        players.getUsers().forEach(this::printPlayerCards);
        System.out.println();
    }

    public void printPlayerCards(final Player player) {
        System.out.printf("%s카드: %s%n", player.getName(), formatCards(player.openCards()));
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

    public void printOptimalPoints(final Players players) {
        Player dealer = players.getDealer();
        System.out.printf("%s카드: %s - 결과: %d%n", dealer.getName(), formatCards(dealer.getCards()), dealer.calculatePoint());

        players.getUsers().forEach((user) -> System.out.printf(
                "%s카드: %s - 결과: %d%n", user.getName(), formatCards(user.getCards()), user.calculatePoint()
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
