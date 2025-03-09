package view;

import static domain.participant.Dealer.THRESHOLD;

import controller.dto.WinLossCountDto;
import domain.participant.Participant;
import domain.result.WinLossResult;
import domain.card.Card;
import domain.card.Hand;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import java.util.stream.Collectors;

public class OutputView {
    public static void printDistributeResult(Players players, Dealer dealer) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("딜러와 ")
                .append(players.getPlayers().stream().map(Player::getName).collect(Collectors.joining(",")))
                .append("에게 2장을 나누었습니다.\n");

        printEveryoneCardsNames(players, dealer, stringBuilder);

        System.out.println(stringBuilder);
    }

    public static void printHandCardsNames(Participant participant) {
        StringBuilder stringBuilder = new StringBuilder();
        openHand(participant, stringBuilder);
        System.out.println(stringBuilder.append("\n"));
    }

    public static void printEveryOneCardsNamesWithTotal(Players players, Dealer dealer) {
        StringBuilder stringBuilder = new StringBuilder();
        openCardsWithTotal(dealer, stringBuilder);
        for (Player player : players.getPlayers()) {
            openCardsWithTotal(player, stringBuilder);
        }
        System.out.println(stringBuilder);
    }

    public static void printBust() {
        System.out.println("버스트가 되어 턴을 종료합니다.");
    }

    public static void printDealerExtraCardsCount(Dealer dealer) {
        int dealerExtraCardsCount = dealer.getExtraHandSize();
        if (dealerExtraCardsCount > 0) {
            System.out.printf("%s는 %d이하라 %d장의 카드를 더 받았습니다.\n\n", dealer.getName(), THRESHOLD, dealerExtraCardsCount);
        }
    }

    public static void printResult(Players players, Dealer dealer, WinLossCountDto winLossCountResult) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("## 최종 승패\n");
        stringBuilder.append(String.format("%s: %d승 %d패 %d무\n", dealer.getName(),
                winLossCountResult.winCount(),
                winLossCountResult.lossCount(),
                winLossCountResult.drawCount()));
        for (Player player : players.getPlayers()) {
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

    private static void openHand(Participant participant, StringBuilder stringBuilder) {
        stringBuilder.append(participant.getName())
                .append("카드: ")
                .append(openAllCards(participant.getHand()));
    }

    private static String openAllCards(Hand hand) {
        return hand.getCards().stream()
                .map(card -> card.getDenomination().getValue() + card.getSuit().getShape())
                .collect(Collectors.joining(", "));
    }

    private static void printEveryoneCardsNames(Players players, Dealer dealer, StringBuilder stringBuilder) {
        stringBuilder.append(String.format("%s카드: ", dealer.getName()));
        stringBuilder.append(getFormattedOpenedCard(dealer.openOneCard()))
                .append(", (???)\n");
        for (Player player : players.getPlayers()) {
            openHand(player, stringBuilder);
            stringBuilder.append("\n");
        }
    }

    private static void openCardsWithTotal(Participant participant, StringBuilder stringBuilder) {
        openHand(participant, stringBuilder);
        int totalScore = participant.getHandTotal();

        String scoreMessage = Integer.toString(totalScore);
        if (totalScore == 0) {
            scoreMessage = "버스트";
        }
        stringBuilder.append(String.format(" - 결과: %s", scoreMessage))
                .append("\n");
    }

}
