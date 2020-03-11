package blackjack.view;

import blackjack.domain.Card.Card;
import blackjack.domain.Card.Cards;
import blackjack.domain.Dealer;
import blackjack.domain.Player;
import blackjack.domain.Players;
import blackjack.domain.result.Result;

import java.util.List;

public class OutputView {

    private static final String DELIMITER = ",";
    private static final String STATUS_INFO_MSG = "딜러와  %s에게 2장의 나누었습니다.";
    private static final String CARD_FINAL_INFO_MSG = "%s: %s - 결과: %d";

    public static void printStatus(List<String> names) {
        String players = String.join(DELIMITER, names);
        System.out.println(String.format(STATUS_INFO_MSG, players));
    }

    public static void printCardInfo(String name, Cards cards) {
        String cardInfo = String.join(DELIMITER, cards.getInfo());
        System.out.println(String.format("%s: %s", name, cardInfo));
    }

    public static void printCardFinalInfo(String name, Cards cards) {
        String cardInfo = String.join(DELIMITER, cards.getInfo());
        System.out.println(String.format(CARD_FINAL_INFO_MSG, name, cardInfo, cards.getSum()));
    }

    public static String getCardInfo(Card card) {
        return Integer.toString(card.getNumber()) + card.getFigure();
    }

    public static void printDealerGetMoreCard(int lowerBound) {
        System.out.println(String.format("딜러는 %d이하라 한장의 카드를 더 받았습니다.", lowerBound));
    }

    public static void printBlackjackResult(Dealer dealer, Players players) {
        StringBuilder dealerMessage = new StringBuilder();
        for (Result result : Result.values()) {
            dealerMessage.append(dealer.getResultSum(result));
            dealerMessage.append(result.getMessage());
        }

        System.out.println("## 최종 승패");
        System.out.println(String.format(String.format("딜러: %s", dealerMessage)));
        for (Player player : players.getPlayers()) {
            System.out.println(String.format("%s: %s", player.getName(), player.getResult().getMessage()));
        }
    }
}
