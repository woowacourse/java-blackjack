package view;

import domain.*;
import domain.card.Card;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;

import java.util.Map;

import static domain.util.BlackJackConstant.INIT_HAND_SIZE;

public class OutputView {

    public void showInitialHands(Dealer dealer, Players players) {
        StringBuilder playerNames = new StringBuilder();

        for (Player player : players.getPlayers()) {
            playerNames.append(player.getName()).append(", ");
        }

        playerNames.delete(playerNames.length() - 2, playerNames.length());
        System.out.printf("\n딜러와 %s에게 %d장을 나누었습니다.\n", playerNames, INIT_HAND_SIZE);

        Card openCard = dealer.getOpenCard();
        System.out.printf("딜러카드: %s%s\n", openCard.getRank().getName(), openCard.getSuit().getSuit());

        for (Player player : players.getPlayers()) {
            System.out.println(printHand(player));
        }

        System.out.println();
    }

    public void showHand(Player player) {
        System.out.println(printHand(player));
    }

    public void showDealerPlayMessage(boolean isPlayToHit) {
        System.out.println();

        if (isPlayToHit) {
            System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
            return;
        }

        System.out.println("딜러는 17이상이므로 카드를 받지 않았습니다.");
    }

    public void showHandsResult(Dealer dealer, Players players) {
        StringBuilder dealerCards = new StringBuilder();
        dealerCards.append("\n딜러카드: ");

        for (Card card : dealer.getHand().getHand()) {
            dealerCards
                    .append(card.getRank().getName())
                    .append(card.getSuit().getSuit())
                    .append(", ");
        }

        dealerCards
                .delete(dealerCards.length() - 2, dealerCards.length())
                .append(" - 결과: ")
                .append(dealer.getHand().calculateSum());
        System.out.println(dealerCards);

        for (Player player : players.getPlayers()) {
            System.out.println(printHand(player)
                    .append(" - 결과: ")
                    .append(player.getHand().calculateSum()));
        }
    }

    private StringBuilder printHand(Player player) {
        StringBuilder playerCards = new StringBuilder();
        playerCards.append(player.getName()).append("카드: ");

        for (Card card : player.getHand().getHand()) {
            playerCards
                    .append(card.getRank().getName())
                    .append(card.getSuit().getSuit())
                    .append(", ");
        }

        playerCards.delete(playerCards.length() - 2, playerCards.length());
        return playerCards;
    }

    public void showDealerResult(Map<MatchResult, Integer> dealerResult) {
        StringBuilder result = new StringBuilder();

        System.out.println("\n## 최종 승패");

        result.append("딜러: ");
        for (Map.Entry<MatchResult, Integer> results : dealerResult.entrySet()) {
            if (results.getValue() != 0) {
                result.append(String.format("%d%s ", results.getValue(), results.getKey().getValue()));
            }
        }

        System.out.println(result);
    }

    public void showPlayerGameResult(Map<String, MatchResult> playerResults) {
        for (Map.Entry<String, MatchResult> results : playerResults.entrySet()) {
            System.out.printf("%s: %s\n", results.getKey(), results.getValue().getValue());
        }
    }

    public static void printErrorMessage(String message) {
        System.out.println(message);
    }
}
