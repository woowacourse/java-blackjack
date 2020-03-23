package view;

import java.util.Map;
import model.result.GameResult;
import model.user.BlackJackGameUser;
import model.user.Dealer;
import model.user.Player;
import model.user.Players;
import model.user.money.Revenue;

import static controller.BlackJackGame.INITIAL_DRAW_COUNT;
import static model.user.Dealer.DEALER_NAME;
import static model.user.Dealer.HIT_BOUNDARY;

public class OutputView {
    public static final String NEW_LINE = "\n";

    public static void printInitialCards(Players players, Dealer dealer) {
        System.out.printf(NEW_LINE + "%s와 %s에세 %d장의 카드를 나누었습니다." + NEW_LINE,
            dealer.getName(), players.getNames(), INITIAL_DRAW_COUNT);
    }

    public static void printUsersCard(Players players, Dealer dealer) {
        printDealerCard(dealer);
        printPlayersCard(players);
    }

    private static void printDealerCard(Dealer dealer) {
        System.out.printf("%s: %s" + NEW_LINE, dealer.getName(), dealer.toStringCardHandFirst());
    }

    private static void printPlayersCard(Players players) {
        for (Player player : players) {
            printPlayerCard(player);
        }
        System.out.println();
    }

    public static void printPlayerCard(Player player) {
        System.out.printf("%s카드: %s" + NEW_LINE, player.getName(), player.toStringCardHand());
    }

    public static void printDealerDraw(Dealer dealer) {
        System.out.printf(NEW_LINE+ "%s는 %d이하라 한장의 카드를 더 받았습니다." + NEW_LINE,
            dealer.getName(), HIT_BOUNDARY);
    }

    public static void printFinalCardHandResult(Players players, Dealer dealer) {
        System.out.println();
        printResultUserCard(dealer);
        for (Player player : players) {
            printResultUserCard(player);
        }
    }

    private static void printResultUserCard(BlackJackGameUser blackJackGameUser) {
        System.out.printf("%s카드: %s - 결과: %d" + NEW_LINE,
            blackJackGameUser.getName(),
            blackJackGameUser.toStringCardHand(),
            blackJackGameUser.getScore());
    }

    public static void printRevenue(final GameResult gameResult) {
        System.out.println(NEW_LINE + "## 최종 수익");
        printDealerRevenue(gameResult.getDealerResult());
        printPlayersRevenue(gameResult.getPlayersResult());
    }

    private static void printDealerRevenue(Revenue result) {
        System.out.printf("%s: %d" + NEW_LINE, DEALER_NAME, (int)result.getRevenue());
    }

    private static void printPlayersRevenue(Map<Player, Revenue> result) {
        for (Player player : result.keySet()) {
            System.out.printf("%s: %d" + NEW_LINE,
                player.getName(), (int)result.get(player).getRevenue());
        }
    }
}