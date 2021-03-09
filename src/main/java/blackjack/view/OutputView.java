package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.game.Result;
import blackjack.domain.game.WinOrLose;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;

import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    public static final String CARDS_INFORMATION = "%s카드: %s";
    public static final String RESULT_INFORMATION = "%s카드: %s - 결과 %s" + System.lineSeparator();
    public static final String DEALER_RESULT = "딜러: %d승 %d무 %d패";

    private OutputView() {
    }

    public static void printMessage(final Object message) {
        System.out.println(message);
    }

    public static void printMessageByFormat(final String format, final Object... message) {
        System.out.printf(format, message);
    }

    public static void printError(Exception e) {
        printMessage(System.lineSeparator() + e.getMessage() + System.lineSeparator());
    }

    public static void printNewLine() {
        System.out.println();
    }

    public static void printDealerCardInfo(final Dealer dealer) {
        printNewLine();
        Card firstCard = dealer.cards().getCards().get(0);
        String oneCardInfo = firstCard.denomination() + firstCard.suit();
        printMessageByFormat(CARDS_INFORMATION, dealer.name(), oneCardInfo);
    }

    public static void printPlayersCardInfo(final Players players) {
        printNewLine();
        players.players()
                .forEach(OutputView::printPlayerCardInfo);
        printNewLine();
    }

    public static void printPlayerCardInfo(final Player player) {
        printMessageByFormat(CARDS_INFORMATION, player.name(), getCardInfo(player));
        printNewLine();
    }

    private static String getCardInfo(final Player player) {
        return player.cards().getCards().stream()
                .map(card -> card.denomination() + card.suit())
                .collect(Collectors.joining(", "));
    }

    public static void printGiveDealer() {
        printNewLine();
        printMessage("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printResult(final Result result) {
        printCardsAndScore(result);
        printNewLine();
        printFinalWinningResult(result);
    }

    private static void printCardsAndScore(final Result result) {
        printDealerResult(result);
        for (Player player : result.getGamblerResult().keySet()) {
            printMessageByFormat(RESULT_INFORMATION, player.name(), getCardInfo(player), player.score().getScore());
        }
    }

    private static void printDealerResult(final Result result) {
        Dealer dealer = result.getDealerInfo();
        String dealerCardInfo = dealer.cards().getCards().stream()
                .map(card -> card.denomination() + card.suit())
                .collect(Collectors.joining(", "));
        printNewLine();
        printMessageByFormat(RESULT_INFORMATION, "딜러", dealerCardInfo, dealer.score().getScore());

    }

    private static void printFinalWinningResult(final Result result) {
        printMessageByFormat(DEALER_RESULT,
                result.countDealerWin(),
                result.countDealerDraw(),
                result.countDealerLose());
        printNewLine();

        Map<Player, WinOrLose> winningTable = result.getGamblerResult();
        for (Player player : winningTable.keySet()) {
            OutputView.printMessage(player.name() + " : " + winningTable.get(player).getSymbol());
        }
    }
}