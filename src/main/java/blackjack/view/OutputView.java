package blackjack.view;

import blackjack.domain.GameStatistic;
import blackjack.domain.card.Cards;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gambler;
import blackjack.domain.player.Gamblers;
import blackjack.domain.player.Player;
import java.util.stream.Collectors;

public class OutputView {

    private static final String DEALER_NO_MORE_CARD_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String INIT_CARD_MESSAGE = "%s와 %s에게 2장의 카드를 나누었습니다.";
    private static final String CARD_STATE_MESSAGE = "%s카드: %s";
    private static final String POINT_STATE_MESSAGE = " - 결과 : %d";
    private static final String PLAYER_RESULT_MESSAGE = "%s: %.0f" + System.lineSeparator();
    private static final String JOIN_DELIMITER = ", ";
    private static final String TOTAL_PROFIT_MESSAGE = "## 최종 수익";

    public static void printInitCardState(Gamblers gamblers, Dealer dealer) {
        System.out.println();
        String gamblerNames = String.join(JOIN_DELIMITER, gamblers.getGamblerNames());
        System.out.printf(INIT_CARD_MESSAGE, dealer.getName(), gamblerNames);
        System.out.println();
    }

    public static void printPlayerCardState(Player player) {
        System.out.printf(CARD_STATE_MESSAGE, player.getName(), printCardList(player.getCards()));
        System.out.println();
    }

    public static void printInitGameState(Gamblers gamblers, Dealer dealer) {
        OutputView.printInitCardState(gamblers, dealer);
        OutputView.printPlayerCardState(dealer);
        for (Gambler gambler : gamblers.getGamblers()) {
            OutputView.printPlayerCardState(gambler);
        }
        System.out.println();
    }

    public static void printCardAndPoint(Gamblers gamblers, Dealer dealer) {
        System.out.println();
        OutputView.printPlayerCardPointState(dealer);
        for (Gambler gambler : gamblers.getGamblers()) {
            OutputView.printPlayerCardPointState(gambler);
        }
    }

    public static void printDealerCardAdded() {
        System.out.println();
        System.out.println(DEALER_NO_MORE_CARD_MESSAGE);
    }

    private static String printCardList(Cards cards) {
        return cards.getCards().stream()
            .map(card -> card.getDenomination().getInitial() + card.getSymbol().getSymbolName())
            .collect(Collectors.joining(JOIN_DELIMITER));
    }

    private static void printPlayerCardPointState(Player player) {
        System.out.printf(CARD_STATE_MESSAGE, player.getName(), printCardList(player.getCards()));
        System.out.printf(POINT_STATE_MESSAGE, player.getPoint());
        System.out.println();
    }

    public static void printGameResult(GameStatistic gameStatistic, Dealer dealer) {
        System.out.println();
        System.out.println(TOTAL_PROFIT_MESSAGE);
        printLossOf(dealer, gameStatistic);
        printProfitOf(gameStatistic);
    }

    private static void printLossOf(Dealer dealer, GameStatistic gameStatistic) {
        System.out.printf(PLAYER_RESULT_MESSAGE, dealer.getName(),
            gameStatistic.calculateTotalLoss());
    }

    private static void printProfitOf(GameStatistic gameStatistic) {
        for (Player player : gameStatistic.getGameResult().keySet()) {
            System.out.printf(PLAYER_RESULT_MESSAGE, player.getName(),
                gameStatistic.getGameResult().get(player));
        }
    }
}
