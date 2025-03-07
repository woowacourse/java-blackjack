package view;

import controller.dto.WinLossCountDto;
import domain.*;

import java.util.List;
import java.util.stream.Collectors;

import static domain.Dealer.THRESHOLD;

public class OutputView {
    public static void printDistributeResult(List<Player> players, Dealer dealer) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("딜러와 ")
                .append(players.stream().map(Player::getName).collect(Collectors.joining(",")))
                .append("에게 2장을 나누었습니다.\n");

        printEveryoneCardsNames(players, dealer, stringBuilder);

        System.out.println(stringBuilder);
    }

    public static void printHandCardsNames(Player player, StringBuilder stringBuilder) {
        openHand(player, stringBuilder);
        System.out.println(stringBuilder.append("\n"));
    }

    public static void printEveryOneCardsNamesWithTotal(List<Player> players, Dealer dealer) {
        StringBuilder stringBuilder = new StringBuilder();
        openCardsWithTotal(dealer, stringBuilder);
        for (Player player : players) {
            openCardsWithTotal(player, stringBuilder);
        }
        System.out.println(stringBuilder);
    }

    public static void printBust() {
        System.out.println("You Die!!");
    }

    public static void printDealerExtraCardsCount(Dealer dealer) {
        int dealerExtraCardsCount = dealer.getExtraHandSize();
        if (dealerExtraCardsCount > 0) {
            System.out.printf("%s는 %d이하라 %d장의 카드를 더 받았습니다.\n\n", dealer.getName(), THRESHOLD, dealerExtraCardsCount);
        }
    }

    public static void printResult(List<Player> players, Dealer dealer, WinLossCountDto winLossCountResult) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("## 최종 승패\n");
        stringBuilder.append(String.format("%s: %d승 %d패 %d무\n", dealer.getName(),
                winLossCountResult.winCount(),
                winLossCountResult.lossCount(),
                winLossCountResult.drawCount()));
        for (Player player : players) {
            stringBuilder.append(String.format("%s: %s\n", player.getName(),
                    WinLossResult.of(player.getWinLoss(dealer.getHandTotal())).getWinLossMessage()));
        }
        System.out.println(stringBuilder);
    }

    public static String getFormattedOpenedCard(Card card) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(card.getDenomination().getValue())
                .append(card.getSuit().getShape());
        return stringBuilder.toString();
    }

    private static void openHand(Player player, StringBuilder stringBuilder) {
        stringBuilder.append(player.getName())
                .append("카드: ")
                .append(openAllCards(player.getHand()));
    }

    private static String openAllCards(Hand hand) {
        return hand.getCards().stream()
                .map(card -> card.getDenomination().getValue() + card.getSuit().getShape())
                .collect(Collectors.joining(", "));
    }

    private static void printEveryoneCardsNames(List<Player> players, Dealer dealer, StringBuilder stringBuilder) {
        stringBuilder.append(String.format("%s카드: ", dealer.getName()));
        stringBuilder.append(getFormattedOpenedCard(dealer.openOneCard()))
                .append(", (???)\n");
        for (Player player : players) {
            openHand(player, stringBuilder);
            stringBuilder.append("\n");
        }
    }

    private static void openCardsWithTotal(Player player, StringBuilder stringBuilder) {
        openHand(player, stringBuilder);
        int totalScore = player.getHandTotal();

        String scoreMessage = Integer.toString(totalScore);
        if (totalScore == 0) {
            scoreMessage = "버스트";
        }
        stringBuilder.append(String.format(" - 결과: %s", scoreMessage))
                .append("\n");
    }

}
