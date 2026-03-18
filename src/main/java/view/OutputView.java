package view;

import domain.card.Card;
import domain.card.MatchResult;
import domain.money.BettingResult;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static util.BlackJackConstant.INIT_HAND_SIZE;

public class OutputView {

    public void showInitialHands(Dealer dealer, Players players) {
        String playerNames = getPlayerNames(players);

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

        List<String> cards = new ArrayList<>();
        for (Card card : dealer.getHand().getHand()) {
            cards.add(card.getRank().getName() + card.getSuit().getSuit());
        }

        dealerCards
                .append(String.join(", ", cards))
                .append(" - 결과: ")
                .append(dealer.getHand().calculateSum());
        System.out.println(dealerCards);

        for (Player player : players.getPlayers()) {
            System.out.println(printHand(player)
                    .append(" - 결과: ")
                    .append(player.getHand().calculateSum()));
        }
    }

    public void showDealerResult(int dealerResult) {
        StringBuilder dealerStatistics = new StringBuilder();

        System.out.println("\n## 최종 승패");

        dealerStatistics.append("딜러: ").append(dealerResult);

        System.out.println(dealerStatistics);
    }

    public void showPlayerGameResult(Map<String, BettingResult> playerResults) {
        for (Map.Entry<String, BettingResult> results : playerResults.entrySet()) {
            System.out.printf("%s: %s\n", results.getKey(), results.getValue().getEarnings());
        }
    }

    public static void printErrorMessage(String message) {
        System.out.println(message);
    }

    private String getPlayerNames(Players players) {
        List<String> names = new ArrayList<>();
        for (Player player : players.getPlayers()) {
            names.add(player.getName());
        }
        return String.join(", ", names);
    }

    private StringBuilder printHand(Player player) {
        StringBuilder playerCards = new StringBuilder();
        playerCards.append(player.getName()).append("카드: ");

        List<String> cards = new ArrayList<>();
        for (Card card : player.getHand().getHand()) {
            cards.add(card.getRank().getName() + card.getSuit().getSuit());
        }

        playerCards.append(String.join(", ", cards));
        return playerCards;
    }
}
