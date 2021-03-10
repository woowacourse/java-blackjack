package blackjack.view;

import static blackjack.domain.user.Dealer.DEALER_HIT_THRESHOLD;

import blackjack.domain.Score;
import blackjack.domain.Status;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.Players;
import blackjack.domain.user.User;
import java.util.List;
import java.util.Map;

public class OutputView {

    private static final String ERROR_MESSAGE_PREFIX = "[ERROR] ";
    private static final String PLAYERS_INPUT_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String HIT_GUIDE_MESSAGE = "%s는 한장의 카드를 더 받겠습니까?(예는 y,아니오는 n)%n";
    private static final String FIRST_CARD_DRAW_MESSAGE = "딜러와 %s에게 2장의 카드를 나누었습니다.%n";
    private static final String RESULT_TITLE_MESSAGE = "## 최종 승패";
    private static final String RESULT_SUFFIX = " - 결과: ";
    private static final String DEALER_WINNING_RESULT_REPORT_MESSAGE = "딜러: %d 승 %d 무 %d 패%n";
    private static final String DEALER_HIT_MESSAGE =
        "딜러는" + DEALER_HIT_THRESHOLD + " 이하라 한장의 카드를 더 받았습니다.";
    private static final String BETTING_MONEY_GUIDE_MESSAGE = "의 배팅 금액은?";
    private static final String SEPARATOR = ": ";
    private static final int DEALER_WIN = 2;
    private static final int DEALER_DRAW = 1;
    private static final int DEALER_LOSE = 0;

    private OutputView() {
    }

    public static void printPlayersGuideMessage() {
        System.out.println(PLAYERS_INPUT_MESSAGE);
    }

    public static void printShowUsersCardMessage(Players players) {
        System.out
            .printf(System.lineSeparator() + FIRST_CARD_DRAW_MESSAGE, players.getPlayersName());
    }

    public static void printUsersFirstDrawCards(Dealer dealer, Players players) {
        System.out.println(dealer.showFirstCard());
        players.getPlayers()
            .forEach(OutputView::printUserCards);
        System.out.println();
    }

    public static void printUserCards(User user) {
        System.out.println(user.showCards());
    }

    public static void printDealerHitMessage() {
        System.out.println(System.lineSeparator() + DEALER_HIT_MESSAGE);
    }

    public static void printCardsAndScore(Dealer dealer, Players players) {
        System.out
            .println(System.lineSeparator() + dealer.showCards() + printScore(dealer.getScore()));
        players.getPlayers().forEach(
            player -> System.out.println(player.showCards() + printScore(player.getScore())));
    }

    private static String printScore(Score score) {
        return RESULT_SUFFIX + score.toInt();
    }

    public static void printResult(List<Integer> matchResult,
        Map<Player, Status> result) {
        printResultTitle();

        System.out.printf(DEALER_WINNING_RESULT_REPORT_MESSAGE, matchResult.get(DEALER_WIN),
            matchResult.get(
                DEALER_DRAW), matchResult.get(DEALER_LOSE));
        result.forEach(
            (player, status) -> System.out
                .println(player.getName() + SEPARATOR + status.getStatus()));
    }

    private static void printResultTitle() {
        System.out.println(System.lineSeparator() + RESULT_TITLE_MESSAGE);
    }

    public static void printHitGuideMessage(Player player) {
        System.out.printf(HIT_GUIDE_MESSAGE, player.getName());
    }

    public static void printErrorMessage(IllegalArgumentException exception) {
        System.out.println(ERROR_MESSAGE_PREFIX + exception.getMessage());
    }

    public static void printPlayerBettingMoneyGuide(String name) {
        System.out.println(name + BETTING_MONEY_GUIDE_MESSAGE);
    }
}
