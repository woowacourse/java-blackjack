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
        System.out.printf("%n딜러와 %s에게 %d장을 나누었습니다.%n", playerNames, STARTING_CARDS_AMOUNT);
    }

    public void printAllParticipantsCards(BlackjackGame game) {
        for (Player player : game.getPlayers()) {
            System.out.println(participantText(player));
        }
        System.out.println(participantText(game.getDealer()));
        System.out.println();
    }

    public void printAllParticipantsCardsWithScore(BlackjackGame game) {
        System.out.println();
        for (Player player : game.getPlayers()) {
            System.out.println(participantText(player) + scoreText(player.score()));
        }
        System.out.println(participantText(game.getDealer()) + scoreText(game.getDealer().score()));
        System.out.println();
    }

    public void printParticipantCards(Participant participant) {
        System.out.println(participantText(participant));
    }

    public void printBustMessage(Participant participant) {
        System.out.println(participant.getName() + "님은 버스트되었습니다.");
    }

    private String scoreText(int score) {
        return " - 결과: " + score;
    }

    private String participantText(Participant participant) {
        return participant.getName() + " 카드: " + cardsText(participant.getCards());
    }

    private String cardsText(List<Card> cards) {
        return cards.stream()
            .map(this::cardText)
            .collect(Collectors.joining(DELIMITER));

    }

    private String cardText(Card card) {
        return card.getRank().getDisplayName() + card.getSymbol().getDisplayName();
    }
}
