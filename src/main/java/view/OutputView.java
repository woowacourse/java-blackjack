package view;

import java.util.List;
import java.util.Map;
import model.card.Card;
import model.participant.Dealer;
import model.participant.Player;
import model.participant.Players;

public class OutputView {
    private static final String JOIN_DELIMITER = ", ";

    public static void printInitialDealResult(Dealer dealer, Players players) {
        printCardDivision(players);
        Card firstDealerCard = dealer.openFirstCard();
        System.out.println("딜러카드: " + firstDealerCard.getCardName());

        for (Player player : players.getPlayers()) {
            List<String> playerCards = player.getHandCards().stream()
                    .map(Card::getCardName)
                    .toList();
            System.out.println(player.getName() + "카드: " + String.join(JOIN_DELIMITER, playerCards));
        }
    }

    public static void printChoiceResult(Player player) {
        List<String> cardsName = player.getHandCards().stream().map(Card::getCardName).toList();
        System.out.println(player.getName() + "카드: " + String.join(JOIN_DELIMITER, cardsName));
    }

    public static void printDealerDealResult() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printFinalScore(Dealer dealer, Players players) {
        System.out.println();
        List<String> dealerCardNames = dealer.getHandCards().stream().map(Card::getCardName).toList();
        System.out.print("딜러카드: " + String.join(JOIN_DELIMITER, dealerCardNames));
        System.out.println(" - 결과: " + dealer.calculateFinalScore());

        for (Player player : players.getPlayers()) {
            List<String> playerCardNames = player.getHandCards().stream().map(Card::getCardName).toList();
            System.out.print(player.getName() + "카드: " + String.join(JOIN_DELIMITER, playerCardNames));
            System.out.println(" - 결과: " + player.calculateFinalScore());
        }
    }

    public static void printDealerFinalResult(int bettingResult) {
        System.out.println();
        System.out.println("## 최종 수익");
        System.out.println("딜러: " + bettingResult);
    }

    public static void printPlayerFinalResult(Map<Player, Integer> playerProfit) {
        for (Player player : playerProfit.keySet()) {
            System.out.println(player.getName() + ": " + playerProfit.get(player));
        }
    }

    private static void printCardDivision(Players players) {
        List<String> playerNames = players.getPlayers().stream()
                .map(Player::getName)
                .toList();
        System.out.printf("\n딜러와 %s에게 2장을 나누었습니다.\n", String.join(JOIN_DELIMITER, playerNames));
    }
}
