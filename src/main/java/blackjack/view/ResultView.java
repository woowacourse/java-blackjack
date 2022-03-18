package blackjack.view;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import blackjack.domain.player.User;
import blackjack.domain.player.Users;
import blackjack.domain.result.GameResult;
import blackjack.domain.result.Result;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

public class ResultView {

    private static final String CARD_MARK_MESSAGE = "카드: ";
    private static final String RESULT_MARK_MESSAGE = " - 결과: ";
    private static final String FINAL_RESULT_MESSAGE = "##최종 승패";
    private static final String DEALER_MARK_MESSAGE = "딜러: ";
    private static final String DEALER_RECEIVE_CARD_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String COLON = ": ";
    private static final String FINAL_REVENUE_MESSAGE = "##최종 수익";

    public static void printPlayersCards(final Dealer dealer, final Users users) {
        printPlayerCards(dealer);
        for (final User user : users.getUsers()) {
            printPlayerCards(user);
        }
    }

    public static void printPlayerCards(final Player player) {
        System.out.println(makePlayerCardsToString(player));
    }

    private static String makePlayerCardsToString(final Player player) {
        StringBuilder sb = new StringBuilder();
        sb.append(player.getName())
                .append(CARD_MARK_MESSAGE)
                .append(checkPlayerType(player));
        return sb.toString();
    }

    private static String checkPlayerType(final Player player) {
        if (player instanceof Dealer) {
            return player.getCardsToList().stream()
                    .map(card -> card.getSuitType() + card.getDenomination())
                    .findFirst()
                    .orElseThrow();
        }
        return player.getCardsToList().stream()
                .map(card -> card.getSuitType() + card.getDenomination())
                .collect(Collectors.joining(", "));
    }

    public static void printDealerReceiveCard() {
        System.out.println();
        System.out.println(DEALER_RECEIVE_CARD_MESSAGE);
    }

    public static void printTotalCardResult(final Dealer dealer, final Users users) {
        System.out.println();
        System.out.println(makeDealerFinalCards(dealer) + RESULT_MARK_MESSAGE + dealer.getTotalScore());
        for (final User user : users.getUsers()) {
            System.out.println(makePlayerCardsToString(user) + RESULT_MARK_MESSAGE + user.getTotalScore());
        }
    }

    private static String makeDealerFinalCards(final Dealer dealer) {
        StringBuilder sb = new StringBuilder();
        String dealerCards = String.join(", ", dealer.getCardsToList().stream()
                .map(card -> card.getSuitType() + card.getDenomination())
                .collect(Collectors.toList()));
        sb.append(dealer.getName())
                .append(CARD_MARK_MESSAGE)
                .append(dealerCards);
        return sb.toString();
    }

    public static void printGameResult(final GameResult gameResult) {
        System.out.println();
        System.out.println(FINAL_RESULT_MESSAGE);
        printDealerGameResult(gameResult.getDealerResult());
        printUsersGameResult(gameResult.getUserResult());
    }

    private static void printDealerGameResult(final Map<Result, Integer> dealerResult) {
        System.out.print(DEALER_MARK_MESSAGE);
        for (final Result result : Result.values()) {
            System.out.print(dealerResult.get(result) + result.getResult() + " ");
        }
        System.out.println();
    }

    private static void printUsersGameResult(final Map<User, Result> userResult) {
        userResult.keySet()
                .stream()
                .sorted(Comparator.comparing(User::getName))
                .forEach(user -> {
                    final Result result = userResult.get(user);
                    System.out.println(user.getName() + COLON + result.getResult());
                });
    }

    public static void printErrorMessage(final Exception e) {
        System.out.println(e.getMessage());
    }

    public static void printDealerRevenue(final int dealerRevenue) {
        System.out.println();
        System.out.println(FINAL_REVENUE_MESSAGE);
        System.out.println(DEALER_MARK_MESSAGE + dealerRevenue);
    }

    public static void printFinalRevenue(final Map<User, Integer> userMoney) {
        userMoney.keySet()
                .stream()
                .sorted(Comparator.comparing(User::getName))
                .forEach(user -> {
                    System.out.println(user.getName() + COLON + userMoney.get(user));
                });
    }
}
