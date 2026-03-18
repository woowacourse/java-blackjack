package view;

import domain.betting.ParticipantsProfitResult;
import domain.dto.InitialDealingResult;
import domain.dto.ParticipantCards;
import java.util.List;

public class OutputView {
    private static final String LINE_FEED = "\n";

    public void printParticipantCard(String name, String cards) {
        System.out.printf("%s카드: %s%n", name, cards);
    }

    public void printParticipantResult(String name, String cards, int score) {
        System.out.printf("%s카드: %s - 결과: %d%n", name, cards, score);
    }

    public void printCompleteDealerTurn() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printNewLine() {
        System.out.println();
    }

    public void printInitialDealingResult(InitialDealingResult initialDealingResult) {
        ParticipantCards dealer = initialDealingResult.dealerUpCard();
        printlnMessage(Formatter.participantCards(dealer));

        List<ParticipantCards> allPlayerCardInHand = initialDealingResult.allPlayerCardInHand();
        printlnMessage(Formatter.participantCards(allPlayerCardInHand));

        println();
    }

    private void printMessage(String message) {
        System.out.print(message);
    }

    private void println() {
        printMessage(LINE_FEED);
    }

    private void printlnMessage(String message) {
        printMessage(message);
        println();
    }

    public void printParticipantsProfit(ParticipantsProfitResult participantsProfitResult) {
        printlnMessage("## 최종 수익");
        printlnMessage(Formatter.participantsProfitResult(participantsProfitResult));
    }
}
