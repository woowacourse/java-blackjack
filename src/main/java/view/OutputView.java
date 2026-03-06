package view;

import domain.Card;

import domain.Dealer;
import domain.GameResult;
import domain.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
        String dealerScore = String.valueOf(dealer.calculateTotalScore());
        if (dealer.isBust()) {
            dealerScore = "버스트";
        }

        System.out.println("딜러카드: " + holdCardToString(dealer.getHoldCards()) + " - 결과: " + dealerScore);

        for (Player player : players) {
            String playerScore = String.valueOf(player.calculateTotalScore());
            if (player.isBust()) {
                playerScore = "버스트";
            }
            System.out.println(
                    player.getName() + "카드: " + holdCardToString(player.getHoldCards()) + " - 결과: " + playerScore);
        }
    }

    public void printDealerFinalCount(int dealerWinningCount, int dealerLosingCount) {
        System.out.println();
        System.out.println("## 최종 승패");
        System.out.println("딜러: " + dealerWinningCount + "승 " + dealerLosingCount + "패");
    }

    public void printPlayerFinalResults(Map<String, GameResult> playerFinalResults) {
        for (Entry<String, GameResult> playerFinalResult : playerFinalResults.entrySet()) {
            System.out.println(playerFinalResult.getKey() + ": " + playerFinalResult.getValue().getValue());
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
