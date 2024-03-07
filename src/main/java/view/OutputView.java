package view;

import domain.card.Card;
import domain.game.BlackjackGame;
import domain.participant.Participant;
import domain.participant.Player;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private static final int STARTING_CARDS_AMOUNT = 2;
    private static final String DELIMITER = ", ";

    public void printDistributionMessage(BlackjackGame game) {
        String playerNames = game.getPlayers().stream()
            .map(Participant::getName)
            .collect(Collectors.joining(DELIMITER));
        System.out.printf("딜러와 %s에게 %d장을 나누었습니다.%n", playerNames, STARTING_CARDS_AMOUNT);
    }

    public void printAllParticipantsCards(BlackjackGame game) {
        for (Player player : game.getPlayers()) {
            printParticipantCards(player);
            System.out.println();
        }
        printParticipantCards(game.getDealer());
        System.out.println();
    }

    public void printAllParticipantsCardsWithScore(BlackjackGame game) {
        for (Player player : game.getPlayers()) {
            printParticipantCards(player);
            printScore(player.score());
        }
        printParticipantCards(game.getDealer());
        printScore(game.getDealer().score());
    }

    public void printParticipantCards(Participant participant) {
        System.out.print(participant.getName());
        System.out.print(" 카드: ");
        System.out.print(cardsText(participant.getCards()));
    }

    private void printScore(int score) {
        System.out.println(" - 결과: " + score);
    }

    private String cardsText(List<Card> cards) {
        return cards.stream()
            .map(this::cardText)
            .collect(Collectors.joining(DELIMITER));

    }

    private String cardText(Card card) {
        return card.getRank().name() + card.getSymbol().name();
    }
}
