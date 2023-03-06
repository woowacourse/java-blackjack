package blackjack.view;

import blackjack.domain.player.*;
import blackjack.domain.result.Result;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    public static final String CARD_USER_DELIMITER = ": ";
    public static final String BLANK = " ";
    public static final String DEALER_CARD = "딜러 카드: ";
    public static final String DEALER = "딜러: ";
    public static final String RESULT_DELIMITER = " - 결과: ";
    public static final String CARD_DELIMITER = "카드: ";
    public static final String RESULT_TITLE = "## 최종 승패";
    public static final String NAME_JOINING_DELIMITER = ", ";

    private OutputView() {
    }

    public static void printReadyMessage(List<String> names) {
        String allName = String.join(NAME_JOINING_DELIMITER, names);
        printEmptyLine();
        System.out.println("딜러와 " + allName + "에게 2장을 나누었습니다.");
    }

    public static void printPlayersCurrentCards(Players players) {
        for (Player player : players.getPlayers()) {
            printPlayerCurrentCards(player);
        }
        printEmptyLine();
    }

    private static void printEmptyLine() {
        System.out.println();
    }

    public static void printPlayerCurrentCards(Player player) {
        String playerCards = getPlayerCards(player);
        System.out.println(player.getPlayerName() + CARD_DELIMITER + playerCards);
        printEmptyLine();
    }

    private static String getPlayerCards(User user) {
        return user.getPlayerCards().stream()
                .map(card -> card.getNumber().getNumber() + card.getSymbol().getSymbol())
                .collect(Collectors.joining(NAME_JOINING_DELIMITER));
    }

    public static void printDealerCurrentCards(Dealer dealer) {
        String dealerCards = dealer.getPlayerCards().stream()
                .map(card -> card.getNumber().getNumber() + card.getSymbol().getSymbol())
                .limit(1)
                .collect(Collectors.joining(""));
        System.out.println(DEALER_CARD + dealerCards);
    }

    public static void printScore(Dealer dealer, Players players) {
        printEmptyLine();
        System.out.println(DEALER_CARD + getPlayerCards(dealer) + RESULT_DELIMITER + dealer.getTotalScore());
        for (Player player : players.getPlayers()) {
            System.out.println(getPlayerCards(player) + RESULT_DELIMITER + player.getTotalScore());
        }
        printEmptyLine();
    }

    public static void printResults(HashMap<Player, Result> playerResults, HashMap<Result, Integer> dealerResults) {
        System.out.println(RESULT_TITLE);
        System.out.print(DEALER);
        dealerResults.keySet().stream()
                .filter(result -> dealerResults.get(result) > 0)
                .forEach(result -> System.out.print(dealerResults.get(result) + result.getResult() + BLANK));
        printEmptyLine();
        for (Player player : playerResults.keySet()) {
            System.out.println(player.getPlayerName() + CARD_USER_DELIMITER + playerResults.get(player).getResult());
        }
    }

    public static void printDealerOneMore() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printScoreUnderLimit() {
        printEmptyLine();
        System.out.println("현재 카드 점수 총합은 21을 초과합니다.");
        printEmptyLine();
    }
}
