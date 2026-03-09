package view;

import domain.Card;

import domain.Dealer;
import domain.GameResult;
import domain.Player;
import domain.Players;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class OutputView {
    public void printStartCardMessage(List<String> playerNames) {
        System.out.println();
        System.out.println("딜러와 " + String.join(", ", playerNames) + "에게 2장을 나누었습니다.");
    }


    public void printStartCard(Players players) {
        for (Player player : players.getPlayers()) {
            printCurrentHoldCard(player);
        }
        System.out.println();
    }

    public void printDealerStartCard(Card firstCard) {
        System.out.println("딜러카드: " + firstCard.toString());
    }

    public void printCurrentHoldCard(Player player) {
        System.out.println(player.getName() + "카드: " + holdCardToString(player.getHand()));
    }

    public void printDealerReceiveCard() {
        System.out.println();
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printFinalScore(Dealer dealer, Players players) {
        System.out.println();
        String dealerScore = String.valueOf(dealer.calculateTotalScore());
        if (dealer.isBust()) {
            dealerScore = "버스트";
        }

        System.out.println("딜러카드: " + holdCardToString(dealer.getHand()) + " - 결과: " + dealerScore);

        for (Player player : players.getPlayers()) {
            String playerScore = String.valueOf(player.calculateTotalScore());
            if (player.isBust()) {
                playerScore = "버스트";
            }
            System.out.println(
                    player.getName() + "카드: " + holdCardToString(player.getHand()) + " - 결과: " + playerScore);
        }
    }

    public void printDealerFinalCount(Map<GameResult, Integer> dealerResults) {
        System.out.println();
        System.out.println("## 최종 승패");
        System.out.println("딜러: " + dealerResults.getOrDefault(GameResult.WIN, 0) + "승 "
                + dealerResults.getOrDefault(GameResult.DRAW, 0) + "무 "
                + dealerResults.getOrDefault(GameResult.LOSE, 0) + "패");
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
