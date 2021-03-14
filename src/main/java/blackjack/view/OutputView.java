package blackjack.view;

import blackjack.domain.Gamer;
import blackjack.domain.Player;
import blackjack.domain.Players;
import blackjack.domain.Result;
import java.util.Map;

public class OutputView {

    private static final String SKELETON_NOTICE_DRAW_TWO_CARDS = "%s와 %s에게 %d장씩 나누었습니다.";
    public static final String NOTICE_RESULT = "## 최종 결과";
    public static final String NOTICE_DEALER_GET_CARD = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String SKELETON_GAME_RESULT_POINT = "%s 카드 : %s - 결과: %d";
    private static final String SKELETON_DEALER_RESULT = "딜러: %d%s %d%s %d%s";
    private static final String SKELETON_NOTICE_GET_MORE_CARD = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
    private static final String SKELETON_DEALER_INFO = "%s: %s";
    private static final String COUPLER_COLON_SPACE = ": ";
    public static final String NOTICE_PROFIT_RESULT = "## 최종 수익";

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
        for (Gamer gamer : players.getAllParticipant()) {
            OutputView.printGameResultPoint(gamer);
        }
    }

    public static void noticeMatchResult(Players players) {
        System.out.println();
        System.out.println(NOTICE_RESULT);
        Map<String, Integer> result = players.judgeResult();

        printDealerResult(result);
        printPlayerResult(players);
    }

    public static void noticeMatchProfit(Players players) {
        System.out.println();
        System.out.println(NOTICE_PROFIT_RESULT);
        for (Gamer gamer : players.getAllParticipant()) {
            System.out.println(gamer.getName() + COUPLER_COLON_SPACE + gamer.getProfit());
        }
    }

    public static void printExceptionMessage(Exception exception) {
        System.out.println(exception.getMessage());
    }

    public static void noticeDealerGetCard() {
        System.out.println(NOTICE_DEALER_GET_CARD);
    }

    public static void printGameResultPoint(Gamer gamer) {
        System.out.println(String
            .format(SKELETON_GAME_RESULT_POINT, gamer.getName(), gamer.getAllCards(),
                gamer.getPoint()));
    }

    private static void printDealerResult(Map<String, Integer> result) {
        System.out.println(
            String.format(SKELETON_DEALER_RESULT,
                result.get(Result.WIN.name()), Result.WIN.getMessage(),
                result.get(Result.DRAW.name()), Result.DRAW.getMessage(),
                result.get(Result.LOSE.name()), Result.LOSE.getMessage()));
    }

    public static void printPlayerResult(Players players) {
        for (Player player : players.getAllPlayers()) {
            System.out.println(player.getName() + COUPLER_COLON_SPACE + player.getResult());
        }
    }

    public static void noticeGetMoreCard(String name) {
        System.out.println(String.format(SKELETON_NOTICE_GET_MORE_CARD, name));
    }

    public static void printPlayerInfo(Gamer player) {
        System.out.println(String.format("%s카드: %s", player.getName(), player.getAllCards()));
    }

    public static void printDealerInfo(Gamer dealer) {
        System.out.println(
            String.format(SKELETON_DEALER_INFO, dealer.getName(), dealer.getOpenCards()));
    }

    public static void noticeBettingMoney(Player player) {
        System.out.println(player.getName() + "의 배팅 금액은?");
    }
}
