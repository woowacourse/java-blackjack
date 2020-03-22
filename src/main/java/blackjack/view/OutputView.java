package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.result.Results;
import blackjack.domain.user.*;

public class OutputView {
    private static final String DELIMITER = ",";
    private static final String SET_INIT_CARDS_MSG = "딜러와  %s에게 2장의 나누었습니다.";
    private static final String DEALER_RECEIVE_MORE_CARD_MSG = "딜러는 %d이하라 한장의 카드를 더 받았습니다.";
    private static final String STATUS_FORMT = "%s: %s";
    private static final String FINAL_STATUS_FORMAT = "%s: %s - 결과: %d";

    public static void printInitialStatus(Players players, Dealer dealer) {
        System.out.println();

        String playesNames = String.join(DELIMITER, players.getPlayerNames());
        System.out.println(String.format(SET_INIT_CARDS_MSG, playesNames));

        printStatus(dealer, dealer.getFirstCard());
        for (Player player : players.getPlayers()) {
            printStatus(player);
        }
        System.out.println();
    }

    public static void printStatus(User user, Card card) {
        String formattedCard = ViewFormatter.formatCard(card);
        String status = String.format(STATUS_FORMT, user.getName(), formattedCard);
        System.out.println(status);
    }

    public static void printStatus(User user) {
        String formattedCards = ViewFormatter.formatCards(user.getCards());
        String status = String.format(STATUS_FORMT, user.getName(), formattedCards);
        System.out.println(status);
    }

    private static void printStatusWithScore(User user) {
        String formattedCards = ViewFormatter.formatCards(user.getCards());
        String status = String.format(FINAL_STATUS_FORMAT, user.getName(), formattedCards, user.createPoint().getPoint());
        System.out.println(status);
    }

    public static void printFinalStatus(Players players, Dealer dealer) {
        System.out.println();
        printStatusWithScore(dealer);

        for (Player player : players.getPlayers()) {
            printStatusWithScore(player);
        }
    }

    public static void printDealerReceiveMoreCard(int lowerBound) {
        System.out.println();
        System.out.println(String.format(DEALER_RECEIVE_MORE_CARD_MSG, lowerBound));
    }

    public static void printFinalResult(Results results) {
        System.out.println();
        System.out.println("## 최종 승패");

        String dealer = ViewFormatter.formatResult(results.getDealerResult());
        System.out.println(dealer);

        results.getPlayerResults()
                .forEach(x -> System.out.println(ViewFormatter.formatResult(x)));
    }
}
