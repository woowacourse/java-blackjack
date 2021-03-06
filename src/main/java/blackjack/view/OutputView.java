package blackjack.view;

import blackjack.domain.card.Cards;
import blackjack.domain.game.Result;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;

import java.util.stream.Collectors;

public class OutputView {
    public static final String RESULT_INFORMATION = "%s카드: %s - 결과 %s" + System.lineSeparator();

    private OutputView() {
    }

    public static void printPlayersCardsInformation(final Players players) {
        for (Player player : players) {
            printPlayerCards(player);
        }
    }

    public static void printPlayerCards(final Player player) {
        printMessageByFormat("%s카드: %s",
                player.getName().getValue(), makeCardInfo(player.getCards()));
        printLineSeparator();
    }

    private static String makeCardInfo(final Cards cards) {
        return cards.cards().stream()
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
        printDealerCardsAndScore(result);
        printGamblerCardsAndScore(result);
    }

    private static void printGamblerCardsAndScore(final Result result){
        for (Player player : result.getGamblerSet()) {
            printMessageByFormat(RESULT_INFORMATION, player.getName().getValue(), makeCardInfo(player.getCards()), player.getScore().getValue());
        }
    }

    private static void printDealerCardsAndScore(final Result result) {
        Cards cards = result.getDealerCards();
        String dealerCardInfo = makeCardInfo(cards);

        printMessageByFormat(RESULT_INFORMATION, "딜러", dealerCardInfo, cards.getScore().getValue());
    }

    private static void printFinalWinningResult(final Result result) {
        printDealerWinningResult(result);
        printGamblerWinningResult(result);
    }

    private static void printDealerWinningResult(Result result) {
        String printFormat = "%s : %s 승 %s 무 %s 패" + System.lineSeparator();

        OutputView.printMessageByFormat(
                printFormat, "딜러", result.countDealerWin(), result.countDealerDraw(), result.countDealerLose()
        );
    }

    private static void printGamblerWinningResult(Result result) {
        for (Player player : result.getGamblerSet()) {
            OutputView.printMessage(player.getName().getValue() + " : " + result.findPlayerWinOrLose(player));
        }
    }

    public static void printLineSeparator() {
        System.out.print(System.lineSeparator());
    }

    public static void printMessage(final Object message) {
        System.out.println(message);
    }

    public static void printMessageByFormat(final String format, final Object... message) {
        System.out.printf(format, message);
    }
}
