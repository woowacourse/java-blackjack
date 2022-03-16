package blackjack.view;

import blackjack.domain.bet.Profit;
import blackjack.domain.game.Dealer;
import blackjack.domain.game.Player;
import blackjack.domain.game.Players;
import blackjack.dto.GamerDto;

import java.util.List;

public class OutputView {

    private static final String ERROR_PREFIX = "[ERROR] ";
    private static final String CARDS_FORMAT = "%s카드: %s";
    private static final String DELIMITER = Players.DELIMITER + " ";

    private OutputView() {
    }

    public static void printExceptionMessage(final String message) {
        System.out.println(ERROR_PREFIX + message);
    }

    public static void printNewLine() {
        System.out.println();
    }

    public static void printPlayerNameInstruction() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
    }

    public static void printBetting(final String name) {
        printNewLine();
        System.out.printf("%s의 배팅 금액은?", name);
        printNewLine();
    }

    public static void printDealCards(final String dealerName, final List<String> playerNames) {
        printNewLine();
        System.out.printf("%s와 %s에게 2장씩 나누었습니다.", dealerName, String.join(DELIMITER, playerNames));
        printNewLine();
    }

    public static void printInitCards(final Dealer dealer, final Players players) {
        printCards(dealer.getName(), GamerDto.getPartOfCards(dealer));
        for (Player player : players.getPlayers()) {
            printCards(player.getName(), GamerDto.getCards(player));
        }
        printNewLine();
    }

    public static void printCards(final String name, final List<String> playingCards) {
        System.out.printf(CARDS_FORMAT, name, String.join(DELIMITER, playingCards));
        printNewLine();
    }

    public static void printDrawInstruction(final String name) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)", name);
        printNewLine();
    }

    public static void printDrawDealer(final String name, final int receivedMaximum) {
        System.out.printf("%s는 %d이하라 한장의 카드를 더 받았습니다.", name, receivedMaximum);
        printNewLine();
    }

    public static void printTotalScore(final Dealer dealer, final Players players) {
        printScore(dealer.getName(), GamerDto.getCards(dealer), dealer.sumOfCards());
        for (Player player : players.getPlayers()) {
            printScore(player.getName(), GamerDto.getCards(player), player.sumOfCards());
        }
        printNewLine();
    }

    public static void printProfit(final Dealer dealer, final Profit profit, final Players players) {
        System.out.println("## 최종 수익");
        printDealerProfit(dealer, profit);
        for (Player player : players.getPlayers()) {
            printPlayerProfit(player, profit);
        }
    }

    private static void printScore(final String name, final List<String> playingCards, final int total) {
        System.out.printf(CARDS_FORMAT + " - 결과: %d", name, String.join(DELIMITER, playingCards), total);
        printNewLine();
    }

    private static void printDealerProfit(final Dealer dealer, final Profit profit) {
        System.out.printf("%s: %d", dealer.getName(), profit.dealerProfit());
        printNewLine();
    }

    private static void printPlayerProfit(final Player player, final Profit profit) {
        System.out.printf("%s: %d", player.getName(), profit.getBetting(player));
        printNewLine();
    }
}
