package ui.output;

import model.card.Card;
import model.user.Dealer;
import model.user.Hand;
import model.user.Participants;
import model.user.Player;
import model.user.Score;

import java.util.List;

import static java.util.stream.Collectors.joining;

public class OutputView {
    private static final String DIVIDE_CARDS_MESSAGE = "딜러와 %s에게 2장을 나누었습니다.";
    private static final String DEALER_GET_CARD = "딜러는 16이하라 한장의 카드를 더 받았습니다.";


    public static void printDivideTwoCard(List<Player> players) {
        String playersNameForm = players.stream()
                .map(Player::getName)
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

    public static void printResult(Participants participants) {
        List<Score> dealerResult = participants.getFinalResult(participants.getDealer());

        int dealerWinCount = getDealerScoreCount(dealerResult, Score.LOSE);
        int dealerLoseCount = getDealerScoreCount(dealerResult, Score.WIN);
        int dealerTieCount = getDealerScoreCount(dealerResult, Score.TIE);

        List<Player> players = participants.getPlayers();

        System.out.println(System.lineSeparator() + "## 최종 승패");
        System.out.println(getDealerResultForm(dealerWinCount, dealerLoseCount, dealerTieCount));
        printPlayersResult(participants, players);
    }

    private static void printPlayersResult(Participants participants, List<Player> players) {
        for (Player player : players) {
            Score score = player.judgeResult(participants.getDealer().calculateTotalValue());
            System.out.printf("%s: %s\n", player.getName(), score.getName());
        }
    }

    private static String getDealerResultForm(int dealerWinCount, int dealerLoseCount, int dealerTieCount) {
        if (dealerTieCount == 0) {
            return String.format("딜러: %d승 %d패\n", dealerWinCount, dealerLoseCount);
        }
        return String.format("딜러: %d승 %d패 %d무\n", dealerWinCount, dealerLoseCount, dealerTieCount);
    }

    private static int getDealerScoreCount(List<Score> dealerResult, Score score) {
        return (int) dealerResult.stream()
                .filter(result -> result == score)
                .count();
    }

}
