package blackjack.view;

import blackjack.domain.user.*;
import blackjack.domain.result.UserResultsDTO;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static blackjack.domain.user.Dealer.DEALER_NAME;

public class OutputView {

    public static final String CARD_USER_DELIMITER = ": ";
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
        System.out.println(dealer.getName() + CARD_DELIMITER + dealerCards);
    }

    public static void printScore(Dealer dealer, Players players) {
        printEmptyLine();
        System.out.println(DEALER_NAME + CARD_DELIMITER + getPlayerCards(dealer) + RESULT_DELIMITER + dealer.getTotalScore());
        for (Player player : players.getPlayers()) {
            System.out.println(player.getName() + CARD_USER_DELIMITER + getPlayerCards(player) + RESULT_DELIMITER + player.getTotalScore());
        }
        printEmptyLine();
    }

    public static void printDealerOneMore() {
        System.out.printf("딜러는 %d이하라 한장의 카드를 더 받았습니다.%n", Dealer.MINIMUM_SCORE);
    }

    public static void printScoreUnderLimit() {
        printEmptyLine();
        System.out.printf("현재 카드 점수 총합은 %d을 초과합니다.%n", HandCards.BUST);
        printEmptyLine();
    }

    public static void printResults(UserResultsDTO userResultsDTO) {
        System.out.println(RESULT_TITLE);
        HashMap<User, String> userResults = userResultsDTO.getResults();
        System.out.println(DEALER_NAME + CARD_USER_DELIMITER + userResultsDTO.removeAndGetDealerResult());
        for (User user : userResults.keySet()) {
            System.out.println(user.getName() + CARD_USER_DELIMITER + userResults.get(user));
        }
    }
}