package view;

import domain.card.Card;
import domain.game.Game;
import domain.participant.Dealer;
import domain.participant.GameParticipant;
import java.util.List;

public class OutputView {
    private static final String DELIMITER = ", ";

    public void displayInitialDeal(Game game) {
        List<GameParticipant> participants = game.getParticipants();
        List<String> playerNames = game.getPlayerNames();

        System.out.printf("%n%s와 %s에게 2장을 나누었습니다.%n", Dealer.NAME, String.join(DELIMITER, playerNames));
        participants.forEach((participant) -> {
            displayNameAndCards(participant.getName(), participant.getCards());
            displayEmptyLine();
        });
        displayEmptyLine();
    }

    public void displayNameAndCards(String name, List<Card> cards) {
        List<String> cardNotations = cards.stream()
                .map(Card::getNotation)
                .toList();
        System.out.printf("%s카드: %s", name, String.join(DELIMITER, cardNotations));
    }

    public void displayDealerHitMessage() {
        System.out.println("딜러는 16이하라 한 장의 카드를 더 받았습니다.");
    }

    public void displayScore(Game game) {
        List<GameParticipant> participants = game.getParticipants();
        for (GameParticipant participant : participants) {
            displayNameAndCards(participant.getName(), participant.getCards());
            System.out.printf(" - 결과: %d%n", participant.calculateScore());
        }
    }

    public void displayProfitMessage() {
        displayEmptyLine();
        System.out.println("## 최종 수익");
    }

    public void displayParticipantProfit(String name, double profit) {
        System.out.printf("%s: %.0f", name, profit);
        displayEmptyLine();
    }

    public void displayEmptyLine() {
        System.out.println();
    }
}
