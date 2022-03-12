package blackjack.view;

import blackjack.domain.GameResult;
import blackjack.domain.Result;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import blackjack.domain.player.User;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ResultView {

    private static final String CARD_MARK_MESSAGE = "카드: ";
    private static final String RESULT_MARK_MESSAGE = " - 결과: ";
    private static final String FINAL_RESULT_MESSAGE = "##최종 승패";
    private static final String DEALER_MARK_MESSAGE = "딜러: ";
    private static final String DEALER_RECEIVE_CARD_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String COLON = ": ";

    public static void printPlayersCards(Dealer dealer, List<User> users) {
        printPlayerCards(dealer);
        for (User user : users) {
            printPlayerCards(user);
        }
    }

    public static void printPlayerCards(Player player) {
        System.out.println(makePlayerCardsToString(player));
    }

    private static String makePlayerCardsToString(Player player) {
        StringBuilder sb = new StringBuilder();
        sb.append(player.getName())
                .append(CARD_MARK_MESSAGE)
                .append(checkPlayerType(player));
        return sb.toString();
    }

    private static String checkPlayerType(Player player) {
        if (player instanceof Dealer) {
            return player.getCards().stream()
                    .map(card -> card.getCardNumberType() + card.getCardPattern())
                    .findFirst()
                    .orElseThrow();
        }
        return String.join(", ", player.getCards().stream()
                .map(card -> card.getCardNumberType() + card.getCardPattern())
                .collect(Collectors.toList()));
    }

    public static void printDealerReceiveCard() {
        System.out.println();
        System.out.println(DEALER_RECEIVE_CARD_MESSAGE);
    }

    public static void printTotalCardResult(Dealer dealer, List<User> users) {
        System.out.println();
        System.out.println(makePlayerCardsToString(dealer) + RESULT_MARK_MESSAGE + dealer.getTotalScore());
        for (User user : users) {
            System.out.println(makePlayerCardsToString(user) + RESULT_MARK_MESSAGE + user.getTotalScore());
        }
    }

    public static void printGameResult(GameResult gameResult) {
        System.out.println();
        System.out.println(FINAL_RESULT_MESSAGE);
        printDealerGameResult(gameResult.getDealerResult());
        printUsersGameResult(gameResult.getUserResult());
    }

    private static void printDealerGameResult(Map<Result, Integer> dealerResult) {
        System.out.print(DEALER_MARK_MESSAGE);
        for (Result result : Result.values()) {
            System.out.print(dealerResult.get(result) + result.getResult() + " ");
        }
        System.out.println();
    }

    private static void printUsersGameResult(Map<String, Result> userResult) {
        for (String userName : userResult.keySet()) {
            System.out.println(userName + COLON + userResult.get(userName).getResult());
        }
    }

    public static void printErrorMessage(Exception e) {
        System.out.println(e.getMessage());
    }
}
