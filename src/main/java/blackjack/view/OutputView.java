package blackjack.view;

import blackjack.domain.card.Cards;
import blackjack.domain.card.Score;
import blackjack.domain.game.Result;
import blackjack.domain.game.WinOrLose;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Name;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;

import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    public static final String CARDS_INFORMATION = "%s카드: %s";
    public static final String RESULT_INFORMATION = "%s카드: %s - 결과 %s" + System.lineSeparator();

    private OutputView() {
    }

    // TODO :: 네이밍
    public static void printPlayers(Players players) {
        for (Player player : players) {
            printPlayer(player);
        }
    }

    public static void printPlayer(Player player) {
        printMessageByFormat(CARDS_INFORMATION, player.getName().name(), getCardInfo(player));
        printMessage("");
    }

    private static String getCardInfo(Player player) {
        return player.getCards().cards().stream()
                .map(card -> card.getDenomination().denomination() + card.getSuit().suit())
                .collect(Collectors.joining(", "));
    }

    public static void printGiveDealer() {
        printMessage("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printResult(final Result result) {

        printCardsAndScore(result);
        printFinalWinningResult(result);
    }

    private static void printCardsAndScore(final Result result) {
        printDealerResult(result);
        for (Player player : result.getGamblerMap().keySet()) {
            printMessageByFormat(RESULT_INFORMATION, player.getName().name(), getCardInfo(player), player.getScore().score());
        }
    }

    private static void printDealerResult(final Result result) {
        Cards cards = result.getDealerCards();
        String dealerCardInfo = cards.cards().stream()
                .map(card -> card.getDenomination().denomination() + card.getSuit().suit())
                .collect(Collectors.joining(", "));

        printMessageByFormat(RESULT_INFORMATION, "딜러", dealerCardInfo, cards.getScore().score());

    }

    private static void printFinalWinningResult(final Result result) {
        OutputView.printMessage(
                "딜러:" + result.countDealerWin() + "승 "
                        + result.countDealerDraw() + "무 "
                        + result.countDealerLose() + "패");

        Map<Player, WinOrLose> winningTable = result.getGamblerMap();
        for (Player player : winningTable.keySet()) {
            OutputView.printMessage(player.getName().name() + " : " + winningTable.get(player).getSymbol());
        }
    }

    public static void printLineSeparator(){
        System.out.println(System.lineSeparator());
    }

    public static void printMessage(final Object message) {
        System.out.println(message);
    }

    public static void printMessageByFormat(final String format, final Object... message) {
        System.out.printf(format, message);
    }
}
