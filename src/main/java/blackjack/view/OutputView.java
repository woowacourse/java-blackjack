package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.result.Result;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.Players;

public class OutputView {
    private static final String DELIMITER = ",";
    private static final String INITIAL_DEAL_INFO_MSG = "딜러와 %s에게 2장의 나누었습니다.";
    private static final String CARD_STATUS_INFO_MSG = "%s카드: %s";
    private static final String ADDITIONAL_DEALER_CARD_MSG = "딜러는 %d이하라 한장의 카드를 더 받았습니다.";
    private static final String CARD_FINAL_INFO_MSG = "%s카드: %s - 결과: %d";
    private static final String FINAL_RESULT_ANNOUNCE_MSG = "## 최종 승패";
    private static final String FINAL_RESULT_MSG = "%s: %s";

    public static void printInitialStatus(Players players, Dealer dealer) {
        System.out.println();
        System.out.println(String.format(INITIAL_DEAL_INFO_MSG, String.join(" ,", players.getPlayerNames())));
        // 딜러 출력
        printStatus(dealer.getName(), dealer.getFirstCard());

        for (Player player : players.getPlayers()) {
            Cards cards = player.getCards();
            printStatus(player.getName(), cards);
        }
        System.out.println();
    }

    public static void printStatus(String name, Card card) {
        System.out.println(String.format(CARD_STATUS_INFO_MSG, name, card.showCardInfo()));
    }

    public static void printStatus(String name, Cards cards) {
        String cardInfo = String.join(DELIMITER, cards.showCardsInfo());
        System.out.println(String.format(CARD_STATUS_INFO_MSG, name, cardInfo));
    }

    private static void printStatus(String name, Cards cards, int score) {
        String cardInfo = String.join(DELIMITER, cards.showCardsInfo());
        System.out.println(String.format(CARD_FINAL_INFO_MSG, name, cardInfo, score));
    }

    public static void printFinalStatus(Players players, Dealer dealer) {
        System.out.println();
        printStatus(dealer.getName(), dealer.getCards(), dealer.computeScore());

        for (Player player : players.getPlayers()) {
            printStatus(player.getName(), player.getCards(), player.computeScore());
        }
    }

    public static void printDealerGetMoreCard(int lowerBound) {
        System.out.println();
        System.out.println(String.format(ADDITIONAL_DEALER_CARD_MSG, lowerBound));
    }

    public static void printFinalResult(Dealer dealer, Players players) {
        System.out.println();
        StringBuilder dealerMessage = new StringBuilder();
        for (Result result : Result.values()) {
            dealerMessage.append(dealer.getResultSum(result));
            dealerMessage.append(result.getMessage());
        }

        System.out.println(FINAL_RESULT_ANNOUNCE_MSG);
        System.out.println(String.format(String.format(FINAL_RESULT_MSG, dealer.getName(), dealerMessage)));
        for (Player player : players.getPlayers()) {
            System.out.println(String.format(FINAL_RESULT_MSG, player.getName(), player.getResult().getMessage()));
        }
    }
}
