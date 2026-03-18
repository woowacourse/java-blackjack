package view;

import static domain.BlackjackGame.INIT_DRAW_COUNT;
import static domain.participant.Dealer.DEALER_DRAW_BOUND;
import static domain.participant.Dealer.DEALER_NAME;

import domain.card.Card;
import domain.card.CurrentHand;
import domain.card.CurrentHands;
import domain.result.BetProfit;
import domain.result.BetProfits;
import domain.result.FinalResult;
import java.util.List;
import java.util.StringJoiner;

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

    public void printInitHand(final CurrentHands initHands) {
        printInitHandInfo(initHands.playerHands());

        printCurrentHand(initHands.dealerHand());
        for (final CurrentHand initHand : initHands.playerHands()) {
            printCurrentHand(initHand);
        }
        System.out.println();
    }

    public void printHitOrStandRequest(final String name) {
        System.out.println(name + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
    }

    public void printCurrentHand(final CurrentHand currentHand) {
        printHand(currentHand);
        System.out.println();
    }

    public void printDealerAdditionalDraw() {
        System.out.printf("%s는 %d이하라 한장의 카드를 더 받았습니다.%s", DEALER_NAME, DEALER_DRAW_BOUND, NEW_LINE);
        System.out.println();
    }

    public void printHandResults(final List<FinalResult> finalResult) {
        for (final FinalResult result : finalResult) {
            printHand(result.currentHand());
            System.out.printf(" - 결과: %d%s", result.score(), NEW_LINE);
        }
        System.out.println();
    }

    public void printWhiteLine() {
        System.out.println();
    }

    public void printBetProfits(final BetProfits results) {
        System.out.println("## 최종 수익");

        printBetProfit(results.dealerProfit());
        printBetProfits(results.betProfits());
    }


    private void printInitHandInfo(final List<CurrentHand> playerHands) {
        System.out.println();
        System.out.printf("%s와 ", DEALER_NAME);
        final StringJoiner players = new StringJoiner(", ");
        for (final CurrentHand playerHand : playerHands) {
            players.add(playerHand.name());
        }
        System.out.printf("%s에게 %d장을 나누었습니다.%s", players, INIT_DRAW_COUNT, NEW_LINE);
    }

    private static void printHand(final CurrentHand currentHand) {
        System.out.printf("%s카드: ", currentHand.name());

        final StringJoiner hand = new StringJoiner(", ");
        for (final Card card : currentHand.cards()) {
            hand.add(card.getCardDescription());
        }
        System.out.print(hand);
    }

    private void printBetProfits(final List<BetProfit> results) {
        for (final BetProfit result : results) {
            printBetProfit(result);
        }
    }

    private void printBetProfit(final BetProfit result) {
        System.out.printf("%s: %,d원", result.name(), result.profit());
        System.out.println();
    }
}
