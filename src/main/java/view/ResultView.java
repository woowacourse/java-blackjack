package view;

import model.Card;
import model.Dealer;
import model.Player;

import java.util.List;

public class ResultView {
    public static void showInitialCards(Dealer dealer, List<Player> players) {
        System.out.println("\n딜러와 " + players.size() + "명에게 2장을 나누었습니다.");
        System.out.println("딜러: " + dealer.getCards().get(0));
        for (Player player : players) {
            System.out.println(player.getName() + " 카드: " + player.getCards().get(0) + ", " + player.getCards().get(1));
        }
        System.out.println();
    }

    public static void showFinalResults(Dealer dealer, List<Player> players) {
        int dealerValue = dealer.getCardsValue();
        System.out.println("\n딜러는 16 이하라 한장의 카드를 더 받았습니다.\n");
        System.out.println("딜러 카드: " + formatCards(dealer.getCards()) + " - 결과: " + dealerValue);
        for (Player player : players) {
            int playerValue = player.getCardsValue();
            System.out.println(player.getName() + " 카드: " + formatCards(player.getCards()) + " - 결과: " + playerValue);
        }
        System.out.println();
    }

    public static String formatCards(List<Card> cards) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cards.size(); i++) {
            sb.append(cards.get(i));
            if (i < cards.size() - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    public static void showFinalOutcome(Dealer dealer, List<Player> players) {
        int dealerWins = 0;
        int dealerLosses = 0;
        int dealerValue = dealer.getCardsValue();

        for (Player player : players) {
            int playerValue = player.getCardsValue();
            if (playerValue > 21 || (dealerValue <= 21 && dealerValue >= playerValue)) {
                dealerWins++;
            } else {
                dealerLosses++;
            }
        }

        System.out.println("## 최종 승패");
        System.out.println("딜러: " + dealerWins + "승 " + dealerLosses + "패");
        for (Player player : players) {
            int playerValue = player.getCardsValue();
            if (playerValue > 21 || (dealerValue <= 21 && dealerValue >= playerValue)) {
                System.out.println(player.getName() + ": 패");
            } else {
                System.out.println(player.getName() + ": 승");
            }
        }
    }
}
