package blackjack.view;

import blackjack.domain.GameResult;
import blackjack.domain.Statistic;
import blackjack.domain.human.Dealer;
import blackjack.domain.human.Human;
import blackjack.domain.human.Player;
import blackjack.domain.human.Players;

public class OutputView {

    public static final String HAVE_CARD = "카드: ";
    public static final String RESULT_PREFIX = " - 결과 : ";
    public static final String DEALER_NO_MORE_CARD_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    public static final String TOTAL_RESULT_MASSAGE = "## 최종 승패";
    public static final String DEALER_RESULT_PREFIX = "딜러: ";
    public static final String COUNT_JOIN_DELIMITER = " ";
    public static final String PLAYER_RESULT_DELIMITER = ": ";
    public static final String INIT_CARD_MESSAGE = "%s와 %s에게 2장의 카드를 나누었습니다.";

    public static void printInitCardState(Players players, Dealer dealer) {
        System.out.printf(System.lineSeparator() + INIT_CARD_MESSAGE + System.lineSeparator(),
            dealer.getName(), players.getPlayerNames());
    }

    public static void printHumanCardState(Human human) {
        String result = human.getName() + HAVE_CARD + human.getCards().toString();
        System.out.println(result);
    }

    public static void printInitGameState(Players players, Dealer dealer) {
        OutputView.printInitCardState(players, dealer);
        OutputView.printHumanCardState(dealer);
        for (Player player : players.getPlayers()) {
            OutputView.printHumanCardState(player);
        }
        System.out.println();
    }

    public static void printCardAndPoint(Players players, Dealer dealer) {
        System.out.println();
        OutputView.printHumanCardPointState(dealer);
        for (Player player : players.getPlayers()) {
            OutputView.printHumanCardPointState(player);
        }
    }

    public static void printHumanCardPointState(Human human) {
        String result =
            human.getName() + HAVE_CARD + human.getCards().toString() + RESULT_PREFIX + human.getPoint();
        System.out.println(result);
    }

    public static void printDealerCardAdded() {
        System.out.println(System.lineSeparator() + DEALER_NO_MORE_CARD_MESSAGE);
    }

    public static void printTotalResult(Statistic statistic) {
        System.out.println(System.lineSeparator() + TOTAL_RESULT_MASSAGE);
        StringBuilder dealerResult = new StringBuilder(DEALER_RESULT_PREFIX);
        for (GameResult gameResult : GameResult.values()) {
            dealerResult.append(
                statistic.getCountByGameResult(gameResult) + gameResult.getResult() + COUNT_JOIN_DELIMITER);
        }
        System.out.println(dealerResult);
    }

    public static void printTotalResultByPlayer(Statistic statistic, Players players) {
        for (Player player : players.getPlayers()) {
            System.out.println(player.getName() + PLAYER_RESULT_DELIMITER + statistic.getGameResultByPlayer(player));
        }
    }
}
