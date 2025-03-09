package view;

import static domain.participant.Dealer.THRESHOLD;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Hand;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Player;
import domain.participant.Players;
import domain.result.WinLossResult;
import java.util.EnumMap;
import java.util.Map;
import java.util.Map.Entry;
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

    public static void printHandCardsNames(final Participant participant) {
        StringBuilder stringBuilder = new StringBuilder();
        openHand(participant, stringBuilder);
        System.out.println(stringBuilder.append("\n"));
    }

    public static void printBust() {
        System.out.println("버스트가 되어 턴을 종료합니다.");
    }

    public static void printDealerExtraCardsCount(final Dealer dealer) {
        int dealerExtraCardsCount = dealer.getExtraHandSize();
        if (dealerExtraCardsCount > 0) {
            System.out.printf("%s는 %d이하라 %d장의 카드를 더 받았습니다.\n\n", dealer.getName(), THRESHOLD, dealerExtraCardsCount);
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

    public static void printAllResult(final Map<Player, WinLossResult> playerResults, final Dealer dealer) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("## 최종 승패\n");

        Map<WinLossResult, Integer> dealerWinLossResult = calculateDealerResult(playerResults);
        stringBuilder.append(String.format("%s: %d승 %d패 %d무\n", dealer.getName(),
                dealerWinLossResult.get(WinLossResult.LOSS),
                dealerWinLossResult.get(WinLossResult.WIN),
                dealerWinLossResult.get(WinLossResult.DRAW)));
        for (Entry<Player, WinLossResult> playerResult : playerResults.entrySet()) {
            stringBuilder.append(String.format("%s: %s\n", playerResult.getKey().getName(),
                    playerResult.getValue().getWinLossMessage()));
        }
        System.out.println(stringBuilder);
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

    private static void openHand(final Participant participant, final StringBuilder stringBuilder) {
        stringBuilder.append(participant.getName())
                .append("카드: ")
                .append(openAllCards(participant.getHand()));
    }

    private static String openAllCards(Hand hand) {
        return hand.getCards().stream()
                .map(card -> mapDenominationToString(card.getDenomination()) + card.getSuit().getShape())
                .collect(Collectors.joining(", "));
    }

    private static void openCardsWithTotal(final Participant participant, final StringBuilder stringBuilder) {
        openHand(participant, stringBuilder);
        int totalScore = participant.getHandTotal();

        String scoreMessage = Integer.toString(totalScore);
        if (totalScore == 0) {
            scoreMessage = "버스트";
        }
        stringBuilder.append(String.format(" - 결과: %s", scoreMessage))
                .append("\n");
    }

    private static Map<WinLossResult, Integer> calculateDealerResult(Map<Player, WinLossResult> playerResults) {
        Map<WinLossResult, Integer> winLossCountResult = new EnumMap<>(WinLossResult.class);

        for (WinLossResult value : WinLossResult.values()) {
            winLossCountResult.put(value, 0);
        }

        for (WinLossResult result : playerResults.values()) {
            winLossCountResult.put(result, winLossCountResult.getOrDefault(result, 0) + 1);
        }
        return winLossCountResult;
    }

    private static String mapDenominationToString(final Denomination denomination) {
        if (denomination == Denomination.ACE) {
            return "A";
        }
        if (denomination == Denomination.TWO) {
            return "2";
        }
        if (denomination == Denomination.THREE) {
            return "3";
        }
        if (denomination == Denomination.FOUR) {
            return "4";
        }
        if (denomination == Denomination.FIVE) {
            return "5";
        }
        if (denomination == Denomination.SIX) {
            return "6";
        }
        if (denomination == Denomination.SEVEN) {
            return "7";
        }
        if (denomination == Denomination.EIGHT) {
            return "8";
        }
        if (denomination == Denomination.NINE) {
            return "9";
        }
        if (denomination == Denomination.TEN) {
            return "10";
        }
        if (denomination == Denomination.JACK) {
            return "J";
        }
        if (denomination == Denomination.QUEEN) {
            return "Q";
        }
        return "K";
    }
}
