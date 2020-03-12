package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.Players;
import blackjack.domain.result.Result;

public class OutputView {

    private static final String DELIMITER = ",";
    private static final String STATUS_INFO_MSG = "딜러와  %s에게 2장의 나누었습니다.";
    private static final String CARD_FINAL_INFO_MSG = "%s: %s - 결과: %d";

    public static void printInitialStatus(Players players, Dealer dealer) {
        System.out.println();

        System.out.println(String.format(STATUS_INFO_MSG, String.join(" ,", players.getPlayerNames())));
        // 딜러 출력
        printStatus(dealer.getName(), dealer.getFirstCard());

        for (Player player : players.getPlayers()) {
            Cards cards = player.getCards();
            printStatus(player.getName(), cards);
        }
        System.out.println();
    }

    public static void printStatus(String name, Card card) {
        System.out.println(String.format("%s: %s", name, card.getInfo()));
    }

    public static void printStatus(String name, Cards cards) {
        String cardInfo = String.join(DELIMITER, cards.getInfo());
        System.out.println(String.format("%s: %s", name, cardInfo));
    }

    private static void printStatus(String name, Cards cards, int score) {
        String cardInfo = String.join(DELIMITER, cards.getInfo());
        System.out.println(String.format("%s: %s - 결과: %d", name, cardInfo, score));
    }

    public static void printFinalStatus(Players players, Dealer dealer) {
        System.out.println();
        printStatus(dealer.getName(), dealer.getCards(), dealer.computeSum());

        for (Player player : players.getPlayers()) {
            printStatus(player.getName(), player.getCards(), player.computeSum());
        }
    }

    public static void printDealerGetMoreCard(int lowerBound) {
        System.out.println();
        System.out.println(String.format("딜러는 %d이하라 한장의 카드를 더 받았습니다.", lowerBound));
    }

    public static void printFinalResult(Dealer dealer, Players players) {
        System.out.println();
        StringBuilder dealerMessage = new StringBuilder();
        for (Result result : Result.values()) {
//            dealerMessage.append(dealer.computeSum(result));
            dealerMessage.append(result.getMessage());
        }

        System.out.println("## 최종 승패");
        System.out.println(String.format(String.format("딜러: %s", dealerMessage)));
        for (Player player : players.getPlayers()) {
            System.out.println(String.format("%s: %s", player.getName(), player.getResult().getMessage()));
        }
    }
}
