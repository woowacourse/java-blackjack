package view;

import domain.bet.Profit;
import domain.card.Card;
import domain.card.Denomination;
import domain.card.Hand;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Player;
import domain.participant.Players;

import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class OutputView {
    private static final Map<Denomination, String> DENOMINATION_NAME_MAP = Map.ofEntries(
            Map.entry(Denomination.ACE, "A"),
            Map.entry(Denomination.TWO, "2"),
            Map.entry(Denomination.THREE, "3"),
            Map.entry(Denomination.FOUR, "4"),
            Map.entry(Denomination.FIVE, "5"),
            Map.entry(Denomination.SIX, "6"),
            Map.entry(Denomination.SEVEN, "7"),
            Map.entry(Denomination.EIGHT, "8"),
            Map.entry(Denomination.NINE, "9"),
            Map.entry(Denomination.TEN, "10"),
            Map.entry(Denomination.JACK, "J"),
            Map.entry(Denomination.QUEEN, "Q"),
            Map.entry(Denomination.KING, "K")
    );
    private static final int THRESHOLD = 16;

    public static void printDistributeResult(Players players, Dealer dealer) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("딜러와 ")
                .append(players.getPlayers().stream().map(Player::getName).collect(Collectors.joining(",")))
                .append("에게 2장을 나누었습니다.\n");

        printEveryoneCardsNames(players, dealer, stringBuilder);

        System.out.println(stringBuilder);
    }

    public static void printHandCardsNames(final String participantName, final Hand participantHand) {
        StringBuilder stringBuilder = new StringBuilder();
        openHand(participantName, participantHand, stringBuilder);
        System.out.println(stringBuilder.append("\n"));
    }

    public static void printBust() {
        System.out.println("버스트가 되어 턴을 종료합니다.");
    }

    public static void printDealerExtraCardsCount(final String dealerName, final int dealerExtraCardsCount) {
        if (dealerExtraCardsCount > 0) {
            System.out.printf("%s는 %d이하라 %d장의 카드를 더 받았습니다.\n\n", dealerName, THRESHOLD, dealerExtraCardsCount);
        }
    }

    public static void printEveryOneCardsNamesWithTotal(final Players players, final Dealer dealer) {
        StringBuilder stringBuilder = new StringBuilder();
        openCardsWithTotal(dealer, stringBuilder);
        for (Player player : players.getPlayers()) {
            openCardsWithTotal(player, stringBuilder);
        }
        System.out.println(stringBuilder);
    }

    public static String getFormattedOpenedCard(final Card card) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(mapDenominationToString(card.getDenomination()))
                .append(card.getSuit().getShape());
        return stringBuilder.toString();
    }

    public static void printAllResult(final Map<String, Profit> playerResults, final String dealerName) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("## 최종 수익\n");

        double totalProfit = 0.0;
        for (Profit profit : playerResults.values()) {
            totalProfit += profit.getProfit();
        }
        stringBuilder.append(String.format("%s: %d\n", dealerName, (int) -totalProfit));
        for (Entry<String, Profit> playerResult : playerResults.entrySet()) {
            stringBuilder.append(String.format("%s: %d\n", playerResult.getKey(),
                    playerResult.getValue().getProfit()));
        }
        System.out.println(stringBuilder);
    }

    public static void printExceptionMessage(final IllegalArgumentException e) {
        System.out.println(e.getMessage());
    }

    private static void printEveryoneCardsNames(Players players, Dealer dealer, StringBuilder stringBuilder) {
        stringBuilder.append(String.format("%s카드: ", dealer.getName()));
        stringBuilder.append(getFormattedOpenedCard(dealer.openOneCard()))
                .append(", (???)\n");
        for (Player player : players.getPlayers()) {
            openHand(player.getName(), player.getHand(), stringBuilder);
            stringBuilder.append("\n");
        }
    }

    private static void openHand(final String participantName, final Hand participantHand, final StringBuilder stringBuilder) {
        stringBuilder.append(participantName)
                .append("카드: ")
                .append(openAllCards(participantHand));
    }

    private static String openAllCards(Hand hand) {
        return hand.getCards().stream()
                .map(card -> mapDenominationToString(card.getDenomination()) + card.getSuit().getShape())
                .collect(Collectors.joining(", "));
    }

    private static void openCardsWithTotal(final Participant participant, final StringBuilder stringBuilder) {
        openHand(participant.getName(), participant.getHand(), stringBuilder);
        int totalScore = participant.getHandTotal();

        String scoreMessage = Integer.toString(totalScore);
        if (totalScore == 0) {
            scoreMessage = "버스트";
        }
        stringBuilder.append(String.format(" - 결과: %s", scoreMessage))
                .append("\n");
    }

    private static String mapDenominationToString(final Denomination denomination) {
        return DENOMINATION_NAME_MAP.get(denomination);
    }
}
