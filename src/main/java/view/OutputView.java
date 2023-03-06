package view;

import domain.card.Card;
import domain.player.Dealer;
import domain.player.DealerStatus;
import domain.player.Player;
import domain.player.Players;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class OutputView {

    private static final String DELIMITER = ", ";
    private static final String NAME_FORMAT = "카드: ";
    private static final String DEALER_WIN = "승";
    private static final String DEALER_LOSE = "패";
    private static final String DEALER_DRAW = "무";
    private static final String PLAYER_WIN = "승";
    private static final String PLAYER_LOSE = "패";
    private static final String PLAYER_DRAW = "무";
    private static final String RESULT_GUIDE_MESSAGE = "## 최종 수익";
    private static final String SCORE_GUIDE_MESSAGE = " - 결과: ";
    private static final String POSTFIX_INITIAL_PICK_GUIDE_MESSAGE = "에게 2장을 나누었습니다.";
    private static final String PREFIX_INITIAL_PICK_GUIDE_MESSAGE = "딜러와 ";
    private static final String COLON = ": ";
    private static final String NEW_LINE = System.lineSeparator();
    private static final String DEALER_DRAW_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String DEALER = "딜러";
    private static final String BUSTED_RESULT_GUIDE_MESSAGE = "Busted";
    private static final int FIRST_INDEX_CARD = 0;
    private static final String BLACKJACK_GUIDE_MESSAGE = "BLACKJACK!";

    private OutputView() {
    }

    public static void printInitialPickGuideMessage(Players players) {
        System.out.print(PREFIX_INITIAL_PICK_GUIDE_MESSAGE);
        List<String> playerNames = getPlayerNames(players);
        System.out.println(String.join(DELIMITER, playerNames) + POSTFIX_INITIAL_PICK_GUIDE_MESSAGE);
        printNewLine();
    }

    private static List<String> getPlayerNames(Players players) {
        return players.getPlayers().stream()
                .map(Player::getName)
                .collect(Collectors.toList());
    }

    public static void printInitialCards(Dealer dealer, Players players) {
        printDealerCard(dealer);
        printInitialCards(players);
    }

    private static void printDealerCard(Dealer dealer) {
        printPlayerName(dealer);
        Card oneCard = dealer.getCards()
                .get(FIRST_INDEX_CARD);
        System.out.println(CardParser.parse(oneCard));
    }

    private static void printPlayerName(Player player) {
        String name = player.getName();
        System.out.print(name + NAME_FORMAT);
    }

    private static void printPlayerCards(Player player) {
        List<String> output = getPlayerCards(player);
        System.out.print(String.join(DELIMITER, output));
    }

    private static List<String> getPlayerCards(Player player) {
        List<String> output = new ArrayList<>();
        for (Card card : player.getCards()) {
            output.add(CardParser.parse(card));
        }
        return output;
    }

    private static void printInitialCards(Players players) {
        players.getPlayers()
                .forEach(OutputView::printSinglePlayer);
    }

    public static void printSinglePlayer(Player player) {
        printPlayerName(player);
        printPlayerCards(player);
        printNewLine();
    }

    public static void printScore(Player player) {
        printPlayerName(player);
        printPlayerCards(player);
        System.out.print(SCORE_GUIDE_MESSAGE);
        if (isSpecialScore(player)) {
            return;
        }
        System.out.print(player.getScore().getValue());
        printNewLine();
    }

    private static boolean isSpecialScore(final Player player) {
        if (player.isBusted()) {
            System.out.println(BUSTED_RESULT_GUIDE_MESSAGE);
            return true;
        }
        if (player.isBlackjack()) {
            System.out.println(BLACKJACK_GUIDE_MESSAGE);
            return true;
        }
        return false;
    }

    public static void printDealerDrawMessage() {
        System.out.println(DEALER_DRAW_MESSAGE);
        printNewLine();
    }

    public static void printResult(Map<Player, Integer> prizeResults) {
        printNewLine();
        System.out.println(RESULT_GUIDE_MESSAGE);
        prizeResults.forEach(
                (player, prize) -> System.out.println(player.getName() + COLON + prize)
        );
    }

    private static void printDealerResult(final List<DealerStatus> dealerStats) {
        StringBuilder dealerStatView = new StringBuilder();
        dealerStatView.append(DEALER).append(COLON);
        Map<DealerStatus, Long> dealerResult = dealerStats.stream()
                .collect(groupingBy(stat -> stat, counting()));
        parseDealerStatus(dealerStatView, dealerResult);
        System.out.println(dealerStatView);
    }

    private static void parseDealerStatus(final StringBuilder dealerStatView, final Map<DealerStatus, Long> dealerResult) {
        Long winCount = dealerResult.get(DealerStatus.WIN);
        Long drawCount = dealerResult.get(DealerStatus.DRAW);
        Long loseCount = dealerResult.get(DealerStatus.LOSE);
        if (winCount != null) {
            dealerStatView.append(winCount).append(DEALER_WIN);
        }
        if (drawCount != null) {
            dealerStatView.append(drawCount).append(DEALER_DRAW);
        }
        if (loseCount != null) {
            dealerStatView.append(loseCount).append(DEALER_LOSE);
        }
        //TODO: containsKey, if문 제거할 메서드 추가
    }

    private static void printPlayersResult(final List<DealerStatus> dealerStats, final Players players) {
        int index = 0;
        for (Player player : players.getPlayers()) {
            System.out.print(player.getName() + COLON);
            parsePlayerStatus(dealerStats, index++);
        }
        printNewLine();
    }

    private static void parsePlayerStatus(final List<DealerStatus> dealerStats, final int index) {
        if (dealerStats.get(index).equals(DealerStatus.WIN)) {
            System.out.println(PLAYER_LOSE);
            return;
        }
        if (dealerStats.get(index).equals(DealerStatus.DRAW)) {
            System.out.println(PLAYER_DRAW);
            return;
        }
        if (dealerStats.get(index).equals(DealerStatus.LOSE)) {
            System.out.println(PLAYER_WIN);
        }
    }

    private static void printNewLine() {
        System.out.print(NEW_LINE);
    }
}
