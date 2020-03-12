package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.Players;
import blackjack.domain.result.ResultType;

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

        printStatus(dealer.getName(), dealer.getFirstCard());
        for (Player player : players.getPlayers()) {
            Cards cards = player.getCards();
            printStatus(player.getName(), cards);
        }
        System.out.println();
    }

    public static void printStatus(String name, Card card) {
        System.out.println(String.format(STATUS_FORMT, name, card.getMessage()));
    }

    public static void printStatus(String name, Cards cards) {
        String cardInfo = String.join(DELIMITER, cards.getMessage());
        System.out.println(String.format(STATUS_FORMT, name, cardInfo));
    }

    private static void printStatus(String name, Cards cards, int score) {
        String cardInfo = String.join(DELIMITER, cards.getMessage());
        System.out.println(String.format(FINAL_STATUS_FORMAT, name, cardInfo, score));
    }

    public static void printFinalStatus(Players players, Dealer dealer) {
        System.out.println();
        printStatus(dealer.getName(), dealer.getCards(), dealer.computeSum());

        for (Player player : players.getPlayers()) {
            printStatus(player.getName(), player.getCards(), player.computeSum());
        }
    }

    public static void printDealerReceiveMoreCard(int lowerBound) {
        System.out.println();
        System.out.println(String.format(DEALER_RECEIVE_MORE_CARD_MSG, lowerBound));
    }

    public static void printFinalResult(Dealer dealer, Players players) {
        System.out.println();
        StringBuilder dealerMessage = new StringBuilder();
        for (ResultType resultType : ResultType.values()) {
            dealerMessage.append(dealer.getResultSum(resultType));
            dealerMessage.append(resultType.getMessage());
        }

        System.out.println("## 최종 승패");
        System.out.println(String.format(String.format(STATUS_FORMT, dealer.getName(), dealerMessage)));
        for (Player player : players.getPlayers()) {
            System.out.println(String.format(STATUS_FORMT, player.getName(), player.getResultTypeMessage()));
        }
    }
}
