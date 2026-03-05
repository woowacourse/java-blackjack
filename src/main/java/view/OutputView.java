package view;

import domain.Card;

import domain.Dealer;
import domain.Player;
import java.util.ArrayList;
import java.util.List;

public class OutputView {
    public void printStartCardMessage(List<String> playerNames) {
        System.out.println();
        System.out.println("딜러와 " + String.join(", ", playerNames) + "에게 2장을 나누었습니다.");
    }


    public void printStartCard(List<Player> players) {
        for (Player player : players) {
            printCurrentHoldCard(player);
        }
        System.out.println();
    }

    public void printDealerStartCard(Card firstCard) {
        System.out.println("딜러카드: " + firstCard.toString());
    }

    public void printCurrentHoldCard(Player player) {
        System.out.println(player.getName() + "카드: " + holdCardToString(player.getHoldCards()));
    }

    public void printDealerReceiveCard() {
        System.out.println();
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printFinalScore(Dealer dealer, List<Player> players) {
        System.out.println();
        System.out.println("딜러카드: " + holdCardToString(dealer.getHoldCards()) + " - 결과: " + dealer.calculateTotalScore());
        for (Player player : players) {
            System.out.println(player.getName() + "카드: " + holdCardToString(player.getHoldCards()) + " - 결과: " + player.calculateTotalScore());
        }
    }

    private String holdCardToString(List<Card> holdCards) {
        List<String> cards = new ArrayList<>();
        for (Card holdCard : holdCards) {
            cards.add(holdCard.toString());
        }
        return String.join(", ", cards);
    }
}
