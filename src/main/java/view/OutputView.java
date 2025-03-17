package view;

import static model.participants.Dealer.THRESHOLD;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import model.card.Card;
import model.card.Hand;
import model.participants.Dealer;
import model.participants.Participants;
import model.participants.Player;
import model.participants.PlayerGroup;

public class OutputView {
    public static void printDistributeResult(Participants participants) {
        List<Player> players = participants.getPlayerGroup().getPlayers();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("딜러와 ")
                .append(players.stream().map(Player::getName).collect(Collectors.joining(",")))
                .append("에게 2장을 나누었습니다.\n");

        printEveryoneCardsNames(participants, stringBuilder);

        System.out.println(stringBuilder);
    }

    public static void printHandCardsNames(Player player) {
        StringBuilder stringBuilder = new StringBuilder();
        openHand(player, stringBuilder);
        System.out.println(stringBuilder.append("\n"));
    }

    public static void printEveryOneCardsNamesWithTotal(PlayerGroup playerGroup, Dealer dealer) {
        StringBuilder stringBuilder = new StringBuilder();
        openCardsWithTotal(dealer, stringBuilder);
        for (Player player : playerGroup.getPlayers()) {
            openCardsWithTotal(player, stringBuilder);
        }
        System.out.println(stringBuilder);
    }

    public static void printDealerExtraCardsCount(Dealer dealer) {
        int dealerExtraCardsCount = dealer.getExtraSize();
        if (dealerExtraCardsCount > 0) {
            System.out.printf("%s는 %d이하라 %d장의 카드를 더 받았습니다.\n\n", dealer.getName(), THRESHOLD, dealerExtraCardsCount);
        }
    }

    public static void printBust() {
        System.out.println("YOU DIE!!!!!!!!!!!!!!!!");
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

    private static void printEveryoneCardsNames(Participants participants, StringBuilder stringBuilder) {
        Dealer dealer = participants.getDealer();
        stringBuilder.append(String.format("%s카드: ", dealer.getName()));
        stringBuilder.append(getFormattedOpenedCard(dealer.openOneCard()))
                .append(", (???)\n");
        for (Player player : participants.getPlayerGroup().getPlayers()) {
            openHand(player, stringBuilder);
            stringBuilder.append("\n");
        }
    }

    public static void printProfitPerParticipant(Map<Player, Integer> profitPerParticipant, Dealer dealer) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(dealer.getName())
                .append(": ")
                .append(profitPerParticipant.get(dealer))
                .append("\n")
        ;

        profitPerParticipant.entrySet().stream().filter(it -> it.getKey().isNotDealer()).forEach(it -> {
            stringBuilder.append(it.getKey().getName())
                    .append(": ")
                    .append(it.getValue())
                    .append("\n");
        });
        System.out.println(stringBuilder);
    }

    private static void openCardsWithTotal(Player player, StringBuilder stringBuilder) {
        openHand(player, stringBuilder);
        String totalScore = Integer.toString(player.getHandTotal());
        if (totalScore.equals("0")) {
            totalScore = "Bust!";
        }
        stringBuilder.append(String.format(" - 결과: %s", totalScore))
                .append("\n");
    }

}
