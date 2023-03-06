package ui.output;

import model.card.Card;
import model.user.Dealer;
import model.user.Hand;
import model.user.Participants;
import model.user.Player;
import model.user.Score;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;
import static model.user.Score.judge;

public class OutputView {
    private static final String DIVIDE_CARDS_MESSAGE = "딜러와 %s에게 2장을 나누었습니다.";
    private static final String DEALER_GET_CARD = "딜러는 16이하라 한장의 카드를 더 받았습니다.";


    public static void printDivideTwoCard(List<Player> players) {
        String playersNameForm = players.stream()
                .map(player -> player.getName())
                .collect(joining(", "));
        System.out.println(System.lineSeparator() + String.format(DIVIDE_CARDS_MESSAGE, playersNameForm));
    }

    public static void printFirstCardStatus(Participants participants) {
        printFirstDealerCardStatus(participants);
        printFirstPlayerCardStatus(participants);
        System.out.print(System.lineSeparator());
    }

    private static void printFirstDealerCardStatus(Participants participants) {
        Dealer dealer = participants.getDealer();
        Card dealerCard = dealer.getHand().getCards().get(0);
        System.out.println(dealer.getName() + ": " + dealerCard.getValue() + dealerCard.getShape());
    }

    private static void printFirstPlayerCardStatus(Participants participants) {
        for (Player player : participants.getPlayers()) {
            printPlayerCardStatus(player);
        }
    }

    public static void printPlayerCardStatus(Player player) {
        System.out.println(player.getName() + ": " + getCardStatusForm(player.getHand()));
    }

    public static void printDealerCardStatus(Dealer dealer) {
        System.out.println(System.lineSeparator()
                + dealer.getName()
                + ": "
                + getCardStatusForm(dealer.getHand())
                + " - 결과: "
                + dealer.getHand().getTotalValue());
    }

    private static String getCardStatusForm(Hand hand) {
        return hand.getCards().stream()
                .map(card -> card.getName() + card.getShape())
                .collect(joining(", "));
    }

    public static void printReceiveCardForDealer() {
        System.out.println(System.lineSeparator() + DEALER_GET_CARD);
    }

    public static void printScoreBoard(Participants participants) {
        Dealer dealer = participants.getDealer();
        List<Player> players = participants.getPlayers();

        printDealerCardStatus(dealer);
        printPlayersScoreBoard(players);
    }

    private static void printPlayersScoreBoard(List<Player> players) {
        for (Player player : players) {
            System.out.println(player.getName()
                    + ": "
                    + getCardStatusForm(player.getHand())
                    + " - 결과: "
                    + player.getHand().getTotalValue()
            );
        }
    }

    public static void printFinalResult(final Participants participants) {
        final Dealer dealer = participants.getDealer();
        final int dealerTotalValue = dealer.calculateTotalValue();

        System.out.println(System.lineSeparator() + "## 최종 승패");
        printDealerFinalResult(participants, dealerTotalValue);
        printPlayersFinalResult(dealerTotalValue, participants.getPlayers());
    }

    private static void printDealerFinalResult(final Participants participants, final int dealerTotalValue) {
        final Dealer dealer = participants.getDealer();
        final List<Player> players = participants.getPlayers();

        final Map<Score, Long> dealerResult = createDealerFinalResult(dealerTotalValue, players);
        createDealerFinalResultForm(dealerResult, dealer);
        System.out.print(System.lineSeparator());
    }

    private static Map<Score, Long> createDealerFinalResult(final int dealerTotalValue, final List<Player> players) {
        return players.stream()
                .map(player -> judge(player.calculateTotalValue(), dealerTotalValue))
                .collect(groupingBy(Function.identity(), counting()));
    }

    private static void createDealerFinalResultForm(final Map<Score, Long> dealerResult, final Dealer dealer) {
        System.out.printf("%s: ", dealer.getName());
        for (final Score score : Score.values()) {
            createDealerFinalResultForm(dealerResult, score);
        }
    }

    private static void createDealerFinalResultForm(final Map<Score, Long> dealerResult, final Score score) {
        final String scoreName = score.getName();

        if (dealerResult.containsKey(score)) {
            System.out.printf("%s%s ", dealerResult.get(score), scoreName);
        }
    }

    private static void printPlayersFinalResult(final int dealerTotalValue, final List<Player> players) {
        for (final Player player : players) {
            final Score playerResult = judge(dealerTotalValue, player.calculateTotalValue());
            System.out.printf("%s: %s%n", player.getName(), playerResult.getName());
        }
    }
}
