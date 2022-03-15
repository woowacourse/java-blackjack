package blackjack.view;

import blackjack.domain.BlackjackTable;
import blackjack.domain.Result;
import blackjack.domain.human.Human;
import blackjack.domain.human.Player;
import java.util.Map;

public final class OutputView {
    
    public static final String drawChar = "무";
    private static final String INIT_CARD_MESSAGE = System.lineSeparator() + "%s와 %s에게 2장의 카드를 나누었습니다."
            + System.lineSeparator();
    private static final String CARD_STATE_MESSAGE = "%s%s: %s";
    private static final String HUMAN_POINT_STATE = " - 결과 : %s";
    private static final String DEALER_CARD_ADDED_MESSAGE = System.lineSeparator() + "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String DEALER_RESULT_MESSAGE = "딜러: %d승 %s%d패" + System.lineSeparator();
    private static final String PLAYER_RESULT_MESSAGE = "%s: %s" + System.lineSeparator();
    private static final String RESULT_FRONT_MESSAGE = System.lineSeparator() + "## 최종 승패";
    private static final String PLAYER_NAME_EXPLANATION = "카드";
    
    public static void printInitCards(final BlackjackTable blackjackTable) {
        OutputView.printInitCardState(blackjackTable);
        OutputView.printHumanHand(blackjackTable.getDealer());
        for (Player player : blackjackTable.getPlayers().get()) {
            OutputView.printHumanHand(player);
        }
        System.out.println();
    }
    
    public static void printHumanHand(final Human human) {
        if (human.getClass().equals(Player.class)) {
            System.out.printf(CARD_STATE_MESSAGE + System.lineSeparator(), human.getName(), PLAYER_NAME_EXPLANATION,
                    human.getCards());
            return;
        }
        System.out.printf(CARD_STATE_MESSAGE + System.lineSeparator(), human.getName(), "",
                human.getInitCard());
    }
    
    private static void printInitCardState(final BlackjackTable blackjackTable) {
        String playersNames = blackjackTable.getPlayers().getPlayerNames().toString();
        System.out.printf(INIT_CARD_MESSAGE,
                blackjackTable.getDealer().getName(),
                playersNames.substring(1, playersNames.length() - 1));
    }
    
    public static void printHandAndPoint(final BlackjackTable blackjackTable) {
        System.out.println();
        OutputView.printHumanCardPointState(blackjackTable.getDealer());
        for (Player player : blackjackTable.getPlayers().get()) {
            OutputView.printHumanCardPointState(player);
        }
    }
    
    public static void printHumanCardPointState(final Human human) {
        String nameExplanation = " 카드";
        if (human.getClass().equals(Player.class)) {
            nameExplanation = "카드";
        }
        System.out.printf(CARD_STATE_MESSAGE + HUMAN_POINT_STATE + System.lineSeparator(),
                human.getName(), nameExplanation, human.getCards(), human.getPoint());
    }
    
    public static void printDealerHit() {
        System.out.println(DEALER_CARD_ADDED_MESSAGE);
    }
    
    public static void printDealerResult(final Map<Result, Integer> result) {
        System.out.println(RESULT_FRONT_MESSAGE);
        String drawResult = result.get(Result.DRAW) + drawChar + " ";
        if (result.get(Result.DRAW) == 0) {
            drawResult = "";
        }
        System.out.printf(DEALER_RESULT_MESSAGE, result.get(Result.WIN), drawResult,
                result.get(Result.LOSE));
    }
    
    public static void printPlayerResult(final Map<Player, Result> result) {
        for (Player player : result.keySet()) {
            System.out.printf(PLAYER_RESULT_MESSAGE, player.getName(), result.get(player));
        }
    }
}
