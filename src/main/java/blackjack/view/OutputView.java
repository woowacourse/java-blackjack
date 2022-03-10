package blackjack.view;

import blackjack.domain.Result;
import blackjack.domain.human.Dealer;
import blackjack.domain.human.Human;
import blackjack.domain.human.Player;
import blackjack.domain.human.Players;
import java.util.Map;

public class OutputView {

    private static final String INIT_CARD_MESSAGE = System.lineSeparator() + "%s와 %s에게 2장의 카드를 나누었습니다."
            + System.lineSeparator();
    private static final String HUMAN_CARD_STATE_MESSAGE = "%s카드: %s";
    private static final String HUMAN_POINT_STATE = " - 결과 : %s";
    private static final String DEALER_CARD_ADDED_MESSAGE = System.lineSeparator() + "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String DEALER_RESULT_MESSAGE = "딜러: %d승 %d무 %d패" + System.lineSeparator();
    private static final String PLAYER_RESULT_MESSAGE = "%s: %s" + System.lineSeparator();

    public static void printInitCardState(final Players players, final Dealer dealer) {
        System.out.printf(INIT_CARD_MESSAGE,
                dealer.getName(), players.getPlayerNames());
    }

    public static void printHumanCardState(final Human human) {
        System.out.printf(HUMAN_CARD_STATE_MESSAGE + System.lineSeparator(), human.getName(), human.getCards());
    }

    public static void printInitCards(final Players players, final Dealer dealer) {
        OutputView.printInitCardState(players, dealer);
        OutputView.printHumanCardState(dealer);
        for (Player player : players.getPlayers()) {
            OutputView.printHumanCardState(player);
        }
        System.out.println();
    }

    public static void printCardAndPoint(final Players players, final Dealer dealer) {
        System.out.println();
        OutputView.printHumanCardPointState(dealer);
        for (Player player : players.getPlayers()) {
            OutputView.printHumanCardPointState(player);
        }
    }

    public static void printHumanCardPointState(final Human human) {
        System.out.printf(HUMAN_CARD_STATE_MESSAGE + HUMAN_POINT_STATE + System.lineSeparator(),
                human.getName(), human.getCards(), human.getPoint());
    }

    public static void printDealerCardAdded() {
        System.out.println(DEALER_CARD_ADDED_MESSAGE);
    }

    public static void printDealerResult(final Map<Result, Integer> dealerResult) {
        System.out.println(System.lineSeparator() + "## 최종 승패");
        System.out.printf(DEALER_RESULT_MESSAGE, dealerResult.get(Result.WIN), dealerResult.get(Result.DRAW),
                dealerResult.get(Result.LOSE));
    }

    public static void printPlayerResult(final Players players) {
        for (Player player : players.getPlayers()) {
            System.out.printf(PLAYER_RESULT_MESSAGE, player.getName(), player.getResult());
        }
    }

    public static void printResult2(final Players players) {
        System.out.println(System.lineSeparator() + "## 최종 승패");
        StringBuilder dealerResult = new StringBuilder();
        int win = 0;
        int draw = 0;
        int lose = 0;
        for (Player player : players.getPlayers()) {
            String playerStatus = player.getName() + ": ";
            playerStatus += player.getResult().getText();
            dealerResult.append(playerStatus).append(System.lineSeparator());
            if (player.getResult().equals(Result.WIN)) {
                lose++;
                continue;
            }
            if (player.getResult().equals(Result.LOSE)) {
                win++;
                continue;
            }
            draw++;
        }
        String start = "딜러: " + win + "승 " + draw + "무 " + lose + "패" + System.lineSeparator();
        System.out.println(start + dealerResult);
    }
}
