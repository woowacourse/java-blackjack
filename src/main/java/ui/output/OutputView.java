package ui.output;

import model.card.Card;
import model.user.Dealer;
import model.user.Hand;
import model.user.Participants;
import model.user.Player;

import java.util.List;

import static java.util.stream.Collectors.joining;

public class OutputView {
    private static final String DIVIDE_CARDS_MESSAGE = "딜러와 %s에게 2장을 나누었습니다.";
    private static final String DEALER_GET_CARD = "딜러는 16이하라 한장의 카드를 더 받았습니다.";


    public static void printDivideTwoCard(final List<Player> players) {
        String playersNameForm = players.stream()
                .map(Player::getName)
                .collect(joining(", "));
        System.out.println(System.lineSeparator() + String.format(DIVIDE_CARDS_MESSAGE, playersNameForm));
    }

    public static void printFirstCardStatus(final Participants participants) {
        printFirstDealerCardStatus(participants);
        printFirstPlayerCardStatus(participants);
        System.out.print(System.lineSeparator());
    }

    private static void printFirstDealerCardStatus(final Participants participants) {
        Dealer dealer = participants.getDealer();
        Card dealerCard = dealer.getHand().getCards().get(0);
        System.out.println(dealer.getName() + ": " + dealerCard.getValue() + dealerCard.getShape());
    }

    private static void printFirstPlayerCardStatus(final Participants participants) {
        for (Player player : participants.getPlayers()) {
            printPlayerCardStatus(player);
        }
    }

    public static void printPlayerCardStatus(final Player player) {
        System.out.println(player.getName() + ": " + getCardStatusForm(player.getHand()));
    }

    public static void printDealerCardStatus(final Dealer dealer) {
        System.out.println();
        printParticipantsScoreBoard(dealer.getName(), dealer.getHand());
    }

    private static String getCardStatusForm(final Hand hand) {
        return hand.getCards().stream()
                .map(card -> card.getName() + card.getShape())
                .collect(joining(", "));
    }

    public static void printReceiveCardForDealer() {
        System.out.println(System.lineSeparator() + DEALER_GET_CARD);
    }

    public static void printScoreBoard(final Participants participants) {
        Dealer dealer = participants.getDealer();
        List<Player> players = participants.getPlayers();

        printDealerCardStatus(dealer);
        printPlayersScoreBoard(players);
    }

    private static void printPlayersScoreBoard(final List<Player> players) {
        for (Player player : players) {
            printParticipantsScoreBoard(player.getName(), player.getHand());
        }
    }

    private static void printParticipantsScoreBoard(String name, Hand hand) {
        System.out.println(name
                + ": "
                + getCardStatusForm(hand)
                + " - 결과: "
                + hand.getTotalValue()
        );
    }

    public static void printGameResult(Participants participants) {
        System.out.println(System.lineSeparator() + "## 최종 수익");
        System.out.println("딜러: " + participants.getDealerProfit());

        List<Player> players = participants.getPlayers();
        for (Player player : players) {
            System.out.println(player.getName() + ": " + player.getMoney());
        }
    }
}
