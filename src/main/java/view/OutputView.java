package view;

import static domain.BlackjackGame.INIT_DRAW_COUNT;
import static domain.participant.Dealer.DEALER_DRAW_BOUND;
import static domain.participant.Dealer.DEALER_NAME;

import domain.card.Card;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Participants;
import domain.participant.Player;
import domain.result.BetResult;
import domain.result.BetResults;
import java.util.List;

public class OutputView {

    private static final String NEW_LINE = System.lineSeparator();

    public void printErrorMessage(final String message) {
        System.out.println();
        System.out.println(message);
    }

    public void printPlayerNamesRequest() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요. (쉼표 기준으로 분리)");
    }

    public void printBetAmountRequest(final String name) {
        System.out.println();
        System.out.println(name + "의 베팅 금액은? (10의 배수만 가능)");
    }

    public void printInitHandCard(final Participants participants) {
        final Dealer dealer = participants.getDealer();
        final List<Player> players = participants.getPlayers();

        printInitHandCardInfo(dealer, players);
        printDealerInitHandCard(dealer);
        printPlayerInitHandCard(players);
    }

    public void printHitOrStandRequest(final String name) {
        System.out.println(name + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
    }

    public void printCurrentHandCard(final Participant participant) {
        printParticipantHandCard(participant);
        System.out.println();
    }

    public void printDealerAdditionalDraw() {
        System.out.printf("%s는 %d이하라 한장의 카드를 더 받았습니다.%s", DEALER_NAME, DEALER_DRAW_BOUND, NEW_LINE);
    }

    public void printHandResults(final Participants participants) {
        System.out.println();
        final Participant dealer = participants.getDealer();
        final List<Player> players = participants.getPlayers();

        printCardResult(dealer);
        printPlayerCardResult(players);
    }

    public void printWhiteLine() {
        System.out.println();
    }

    public void printBetResults(final BetResults results) {
        System.out.println();
        System.out.println("## 최종 수익");

        printBetResult(results.dealerResult());
        printBetResults(results.betResults());
    }


    private static void printInitHandCardInfo(final Dealer dealer, final List<Player> players) {
        final StringBuilder playerNames = new StringBuilder();
        System.out.println();
        System.out.printf("%s와 ", dealer.getName());
        for (final Participant player : players) {
            playerNames.append(player.getName()).append(", ");
        }
        playerNames.delete(playerNames.length() - 2, playerNames.length());
        System.out.printf("%s에게 %d장을 나누었습니다.%s", playerNames, INIT_DRAW_COUNT, NEW_LINE);
    }

    private static void printDealerInitHandCard(final Participant dealer) {
        System.out.printf("%s카드: %s%s", dealer.getName(), dealer.getHand().getFirst().getCardDescription(), NEW_LINE);
    }

    private static void printPlayerInitHandCard(final List<Player> players) {
        for (final Player player : players) {
            printParticipantHandCard(player);
            System.out.println();
        }
        System.out.println();
    }

    private static void printParticipantHandCard(final Participant player) {
        System.out.printf("%s카드: ", player.getName());

        final StringBuilder cardDescriptions = new StringBuilder();
        final List<Card> handCards = player.getHand();
        for (final Card handCard : handCards) {
            cardDescriptions.append(handCard.getCardDescription()).append(", ");
        }
        cardDescriptions.delete(cardDescriptions.length() - 2, cardDescriptions.length());
        System.out.print(cardDescriptions);
    }

    private static void printCardResult(final Participant dealer) {
        printParticipantHandCard(dealer);
        printScore(dealer);
    }

    private static void printPlayerCardResult(final List<Player> players) {
        for (final Participant player : players) {
            printCardResult(player);
        }
    }

    private static void printScore(final Participant dealer) {
        System.out.printf(" - 결과: %d%s", dealer.getScore(), NEW_LINE);
    }

    private void printBetResults(final List<BetResult> results) {
        for (final BetResult result : results) {
            printBetResult(result);
        }
    }

    private void printBetResult(final BetResult result) {
        System.out.printf("%s: %,d원", result.name(), result.profit());
        System.out.println();
    }
}
