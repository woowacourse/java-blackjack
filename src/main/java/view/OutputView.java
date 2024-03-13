package view;

import domain.game.BlackjackGame;
import domain.game.Result;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Player;

public class OutputView {

    private static final int STARTING_CARDS_AMOUNT = 2;

    private final MessageResolver resolver;

    public OutputView(MessageResolver resolver) {
        this.resolver = resolver;
    }

    public void printDistributionMessage(BlackjackGame game) {
        System.out.printf("%n딜러와 %s에게 %d장을 나누었습니다.%n",
            resolver.playerNamesText(game.getPlayers()),
            STARTING_CARDS_AMOUNT);
    }

    public void printStartingCardsOfAllParticipants(BlackjackGame game) {
        System.out.println(resolver.dealerNameAndStartingCardsText(game.getDealer()));
        for (Player player : game.getPlayers()) {
            printNameAndCardsOfParticipant(player);
        }
        System.out.println();
    }

    public void printNameAndCardsOfParticipant(Participant participant) {
        System.out.println(resolver.participantNameAndCardsText(participant));
    }

    public void printBustMessage(Participant participant) {
        System.out.println(participant.getName() + "님은 버스트되었습니다.");
    }

    public void printDealerDrawMessage() {
        System.out.printf("%n딜러는 %d이하라 한장의 카드를 더 받았습니다.%n", Dealer.THRESHOLD_SCORE);
    }

    public void printFinalCardsAndScoresOfAllParticipants(BlackjackGame game) {
        System.out.println();
        printFinalCardsAndScoresOfParticipant(game.getDealer());
        for (Player player : game.getPlayers()) {
            printFinalCardsAndScoresOfParticipant(player);
        }
        System.out.println();
    }

    public void printFinalCardsAndScoresOfParticipant(Participant participant) {
        System.out.printf("%s - 결과: %s%n",
            resolver.participantNameAndCardsText(participant),
            resolver.scoreText(participant.score()));
    }

    public void printWinLoseOfAllParticipants(BlackjackGame game, Result result) {
        System.out.println("## 최종 승패");
        System.out.println(resolver.dealerResultText(game.getDealer(), result));
        for (Player player : game.getPlayers()) {
            System.out.println(resolver.playerResultText(player, result));
        }
    }
}
