package view;

import static domain.participant.dealer.Dealer.DEALER_HIT_THRESHOLD;
import static domain.participant.dealer.Dealer.INITIAL_CARD_COUNT;

import domain.card.Hand;
import domain.participant.Participant;
import domain.participant.attributes.Name;
import domain.participant.dealer.Dealer;
import domain.participant.dealer.DealerResult;
import domain.participant.player.PlayerResults;
import domain.participant.player.Players;
import domain.result.BlackjackResult;
import domain.result.Profit;

public class ResultView implements BlackjackViewParser {

    private static final String PARTICIPANT_NAME_AND_CARDS = "%n%s: %s";

    public void printInitialCards(final Dealer dealer, final Players players) {
        printInitialDealMessage(dealer, players);
        printDealerHand(dealer);
        printPlayerHands(players);
        System.out.println();
    }

    private void printInitialDealMessage(final Dealer dealer, final Players players) {
        System.out.printf(
                "%n%s와 %s에게 %d장을 나누었습니다.%n",
                parseName(dealer.name()),
                parsePlayerNames(players),
                INITIAL_CARD_COUNT
        );
    }

    private void printDealerHand(final Dealer dealer) {
        System.out.printf(
                PARTICIPANT_NAME_AND_CARDS,
                parseName(dealer.name()),
                parseCard(dealer.peek())
        );
    }

    public void printParticipantHand(final Participant participant) {
        Hand hand = participant.hand();
        System.out.printf(
                PARTICIPANT_NAME_AND_CARDS,
                parseName(participant.name()),
                parseHand(hand)
        );
    }

    public void printPlayerHands(final Players players) {
        players.forEach(this::printParticipantHand);
    }

    public void printDealerHit(final Dealer dealer) {
        System.out.printf(
                "%n%s는 %s이하라 한장의 카드를 더 받습니다.%n",
                parseName(dealer.name()),
                DEALER_HIT_THRESHOLD
        );
    }

    public void printCardsAndTotalOf(final Dealer dealer, final Players players) {
        printCardAndSum(dealer);
        players.forEach(this::printCardAndSum);
        System.out.println();
    }

    private void printCardAndSum(final Participant participant) {
        Hand hand = participant.hand();
        System.out.printf(
                PARTICIPANT_NAME_AND_CARDS + " - 결과: %d",
                parseName(participant.name()),
                parseHand(hand),
                participant.score()
        );
    }

    public void printResult(final BlackjackResult result) {
        System.out.println("\n## 최종 수익");
        printDealerResult(result.getDealerResult());
        printPlayerResults(result.getPlayerResults());
    }

    public void printDealerResult(final DealerResult result) {
        Name name = result.getDealerName();
        Profit profit = result.getProfit();
        String message = parseName(name) + ": " + profit.amount();
        System.out.println(message);
    }

    public void printPlayerResults(final PlayerResults result) {
        Players players = result.getPlayers();
        String message = players
                .toList()
                .stream()
                .map(player -> parseName(player.name()) + ": " + result.profitOf(player).amount())
                .reduce((result1, result2) -> result1 + "\n" + result2)
                .orElse("");
        System.out.println(message);
    }
}
