package view;

import java.util.List;
import view.dto.InitialDealingResult;
import view.dto.ParticipantCards;
import view.dto.ParticipantsProfit;

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

    public void printParticipantsProfit(ParticipantsProfit participantsProfit) {
        printlnMessage("## 최종 수익");
        printlnMessage(Formatter.participantsProfitResult(participantsProfit));
    }
}
