package ui.output;

import model.card.Card;
import model.user.Dealer;
import model.user.Hand;
import model.user.Participants;
import model.user.Player;
import model.user.Result;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;
import static model.user.Result.judge;

public class OutputView {
    private static final String DIVIDE_CARDS_MESSAGE = "딜러와 %s에게 2장을 나누었습니다.";
    private static final String DEALER_GET_CARD = "딜러는 16이하라 한장의 카드를 더 받았습니다.";


    public void printDivideTwoCard(List<Player> players) {
        String playersNameForm = players.stream()
                .map(Player::getName)
                .collect(joining(", "));
        System.out.println(System.lineSeparator() + String.format(DIVIDE_CARDS_MESSAGE, playersNameForm));
    }

    public void printFirstCardStatus(Participants participants) {
        printFirstDealerCardStatus(participants);
        printFirstPlayerCardStatus(participants);
        System.out.print(System.lineSeparator());
    }

    private void printFirstDealerCardStatus(Participants participants) {
        Dealer dealer = participants.getDealer();
        Card dealerCard = dealer.getHand().getCards().get(0);
        System.out.println(dealer.getName() + ": " + dealerCard.getValue() + dealerCard.getShape());
    }

    private void printFirstPlayerCardStatus(Participants participants) {
        for (Player player : participants.getPlayers()) {
            printPlayerCardStatus(player);
        }
    }

    public void printPlayerCardStatus(Player player) {
        System.out.println(player.getName() + ": " + getCardStatusForm(player.getHand()));
    }

    public void printDealerCardStatus(Dealer dealer) {
        System.out.println(System.lineSeparator()
                + dealer.getName()
                + ": "
                + getCardStatusForm(dealer.getHand())
                + " - 결과: "
                + dealer.getHand().getTotalValue());
    }

    private String getCardStatusForm(Hand hand) {
        return hand.getCards().stream()
                .map(card -> card.getName() + card.getShape())
                .collect(joining(", "));
    }

    public void printReceiveCardForDealer() {
        System.out.println(System.lineSeparator() + DEALER_GET_CARD);
    }

    public void printScoreBoard(Participants participants) {
        Dealer dealer = participants.getDealer();
        List<Player> players = participants.getPlayers();

        printDealerCardStatus(dealer);
        printPlayersScoreBoard(players);
    }

    private void printPlayersScoreBoard(List<Player> players) {
        for (Player player : players) {
            System.out.println(player.getName()
                    + ": "
                    + getCardStatusForm(player.getHand())
                    + " - 결과: "
                    + player.getHand().getTotalValue()
            );
        }
    }

    public void printFinalResult(final Participants participants) {
        final Dealer dealer = participants.getDealer();
        final int dealerTotalValue = dealer.calculateTotalValue();

        System.out.println(System.lineSeparator() + "## 최종 승패");
        printDealerFinalResult(participants, dealerTotalValue);
        printPlayersFinalResult(dealerTotalValue, participants.getPlayers());
    }

    private void printDealerFinalResult(final Participants participants, final int dealerTotalValue) {
        final Dealer dealer = participants.getDealer();
        final List<Player> players = participants.getPlayers();

        final Map<Result, Long> dealerResult = createDealerFinalResult(dealerTotalValue, players);
        createDealerFinalResultForm(dealerResult, dealer);
        System.out.print(System.lineSeparator());
    }

    private Map<Result, Long> createDealerFinalResult(final int dealerTotalValue, final List<Player> players) {
        return players.stream()
                .map(player -> judge(player.calculateTotalValue(), dealerTotalValue))
                .collect(groupingBy(Function.identity(), counting()));
    }

    private void createDealerFinalResultForm(final Map<Result, Long> dealerResult, final Dealer dealer) {
        System.out.printf("%s: ", dealer.getName());
        for (final Result result : Result.values()) {
            createDealerFinalResultForm(dealerResult, result);
        }
    }

    private void createDealerFinalResultForm(final Map<Result, Long> dealerResult, final Result result) {
        final String scoreName = result.getName();

        if (dealerResult.containsKey(result)) {
            System.out.printf("%s%s ", dealerResult.get(result), scoreName);
        }
    }

    private void printPlayersFinalResult(final int dealerTotalValue, final List<Player> players) {
        for (final Player player : players) {
            final Result playerResult = judge(dealerTotalValue, player.calculateTotalValue());
            System.out.printf("%s: %s%n", player.getName(), playerResult.getName());
        }
    }
}
