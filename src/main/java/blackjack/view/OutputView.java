package blackjack.view;

import static blackjack.domain.Players.COUPLER_COMMA_SPACE;
import static blackjack.domain.Players.DRAW;
import static blackjack.domain.Players.LOSE;
import static blackjack.domain.Players.RESULT_DRAW;
import static blackjack.domain.Players.RESULT_LOSE;
import static blackjack.domain.Players.RESULT_WIN;
import static blackjack.domain.Players.WIN;

import blackjack.domain.GameResult;
import blackjack.domain.Gamer;
import blackjack.domain.Player;
import blackjack.domain.Players;
import java.util.Map;

public class OutputView {

    private static final String SKELETON_NOTICE_DRAW_TWO_CARDS = "%s와 %s에게 %d장씩 나누었습니다.";
    public static final String NOTICE_RESULT = "## 최종 승패";
    public static final String NOTICE_DEALER_GET_CARD = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String SKELETON_GAME_RESULT_POINT = "%s - 결과: %d";
    private static final String SKELETON_DEALER_RESULT = "딜러: %d%s %d%s %d%s";
    private static final String SKELETON_NOTICE_GET_MORE_CARD = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";

    public static void noticeDrawTwoCards(Players players) {
        System.out.println();
        System.out.println(
            String.format(SKELETON_NOTICE_DRAW_TWO_CARDS, players.getDealerName(),
                players.getPlayerNames(), Gamer.NUM_INIT_CARD));
    }

    public static void noticePlayersCards(Players players) {
        System.out.print(players.getPlayersCards());
        System.out.println();
    }

    public static void noticePlayersPoint(Players players) {
        System.out.println();
        GameResult.getPlayersCardsAndResult(players);
    }

    public static void noticeResult(Players players) {
        System.out.println();
        System.out.println(NOTICE_RESULT);
        GameResult.getResult(players);
    }

    public static void printExceptionMessage(IllegalArgumentException exception) {
        System.out.println(exception.getMessage());
    }

    public static void noticeDealerGetCard() {
        System.out.println(NOTICE_DEALER_GET_CARD);
    }

    public static void printGameResultPoint(Gamer gamer) {
        System.out.println(String
            .format(SKELETON_GAME_RESULT_POINT, gamer.getInfo(), gamer.calcPoint()));
    }

    public static void printDealerResult(Map<String, Integer> result) {

        System.out.println(
            String.format(SKELETON_DEALER_RESULT, result.get(WIN), RESULT_WIN, result.get(DRAW),
                RESULT_DRAW, result.get(LOSE), RESULT_LOSE));
    }

    public static void printPlayerResult(Players players) {
        for (Player player : players.getAllPlayers()) {
            System.out.println(player.getName() + COUPLER_COMMA_SPACE + player.getResult());
        }
    }

    public static void noticeGetMoreCard(String name) {
        System.out.println(String.format(SKELETON_NOTICE_GET_MORE_CARD, name));
    }

    public static void printPlayerInfo(String info) {
        System.out.println(info);
    }
}
