package view;

import domain.betting.Profits;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Player;
import domain.participant.Players;
import domain.result.Results;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private static final String INIT_MESSAGE_FORMAT = "\n딜러와 %s에게 2장의 나누었습니다.\n";
    private static final String SHOW_DEALER_FIRST_HAND_FORMAT = "딜러: %s\n";
    private static final String SHOW_HAND_FORMAT = "%s 카드: %s\n";
    private static final String STATUS_FORMAT = "%s 카드: %s - 결과 : %d\n";
    private static final String BUST_MESSAGE = "[ 🧨💥🧨 Bust!!! 💣💥💣 ]";
    private static final String MAX_SCORE_MESSAGE = "[ SCORE IS 21 ]";
    private static final String RESULT_TITLE_MESSAGE = "\n## 최종 승패";
    private static final String DEALER_RESULT_MESSAGE_FORMAT = "딜러: %d승 %d무 %d패\n";
    private static final String PLAYER_RESULT_MESSAGE_FORMAT = "%s: %s\n";
    private static final String DEALER_DRAW_MESSAGE = "\n딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String DEALER_BLACK_JACK_MESSAGE = "\n== DEALER IS BLACK JACK ==";
    private static final String PLAYER_IS_BLACK_JACK_MESSAGE = "\n💵🤑💵 %s IS BLACK JACK 🎰🤑🎰\n";
    private static final String PROFIT_TITLE_MESSAGE = "\n## 최종 수익";
    private static final String PROFIT_OF_DEALER_FORMAT = "딜러: %s\n";
    private static final String PROFIT_OF_PLAYER_FORMAT = "%s: %s\n";

    public static void printInitHands(Dealer dealer, Players players) {
        printInitHandsMessage(players.getNames());
        printNewLine();
        System.out.printf(SHOW_DEALER_FIRST_HAND_FORMAT, dealer.getFirstHand().combineRankAndSuit());
        players.forEach(player -> printPlayerHand(player.getName().getValue(), player.showHand()));
    }

    public static void printInitHandsMessage(List<Name> names) {
        String namesForPrint = names.stream().map(Name::getValue).collect(Collectors.joining(", "));
        System.out.printf(INIT_MESSAGE_FORMAT, namesForPrint);
    }

    public static void printPlayerHand(String name, String hand) {
        System.out.printf(SHOW_HAND_FORMAT, name, hand);
    }

    public static void printDealerIsBlackJackMessage(Dealer dealer) {
        if (dealer.isBlackJack()) {
            System.out.println(DEALER_BLACK_JACK_MESSAGE);
        }
    }

    public static void printPlayerIsBlackJackMessage(Players players) {
        players.forEach(player -> OutputView.printIfPlayerIsBlackJackMessage(player));
    }

    private static void printIfPlayerIsBlackJackMessage(Player player) {
        if (player.isBlackJack()) {
            System.out.printf(PLAYER_IS_BLACK_JACK_MESSAGE, player.getName().getValue());
        }
    }

    public static void printIfMaxScoreOrBust(Players players) {
        players.forEach(player -> {
            if (player.isUpperBoundScore()) {
                System.out.println(MAX_SCORE_MESSAGE);
            }
            if (player.isBust()) {
                System.out.println(BUST_MESSAGE);
            }
        });
    }

    public static void printDealerDrawMessage() {
        System.out.println(DEALER_DRAW_MESSAGE);
    }

    public static void printStatuses(Dealer dealer, Players players) {
        printNewLine();
        System.out.printf(STATUS_FORMAT, "딜러", dealer.showHand(), dealer.calculateBestScore());
        players.forEach(player -> {
            System.out.printf(
                    STATUS_FORMAT,
                    player.getName().getValue(),
                    player.showHand(),
                    player.calculateBestScore()
            );
        });
    }

    public static void printResult(List<Name> names, Results results) {
        OutputView.printResultTitle();
        OutputView.printResultOfDealer(results.countDealerWin(), results.countDealerDraw(), results.countDealerLose());
        for (Name name : names) {
            OutputView.printResultOfPlayer(name.getValue(), results.getVersusOfPlayer(name).getResult());
        }
    }

    private static void printResultTitle() {
        System.out.println(RESULT_TITLE_MESSAGE);
    }

    private static void printResultOfDealer(int winCount, int drawCount, int loseCount) {
        System.out.printf(DEALER_RESULT_MESSAGE_FORMAT, winCount, drawCount, loseCount);
    }

    private static void printResultOfPlayer(String name, String result) {
        System.out.printf(PLAYER_RESULT_MESSAGE_FORMAT, name, result);
    }

    public static void printProfit(List<Name> names, Profits profits) {
        OutputView.printProfitTitle();
        OutputView.printProfitOfDealer(profits.calculateDealerProfit());
        for (Name name : names) {
            OutputView.printProFitOfPlayer(name.getValue(), profits.getProfit(name));
        }
    }

    private static void printProfitTitle() {
        System.out.println(PROFIT_TITLE_MESSAGE);
    }

    private static void printProfitOfDealer(double profit) {
        System.out.printf(PROFIT_OF_DEALER_FORMAT, String.format("%.1f", profit));
    }

    private static void printProFitOfPlayer(String name, double profit) {
        System.out.printf(PROFIT_OF_PLAYER_FORMAT, name, String.format("%.1f", profit));
    }

    private static void printNewLine() {
        System.out.println();
    }
}
