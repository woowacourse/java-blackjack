package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.game.Participant;
import blackjack.domain.game.Players;
import blackjack.domain.result.ParticipantResult;
import blackjack.domain.result.ParticipantResults;
import java.util.List;

public final class OutputView {

    private static final String DELIMITER = ", ";

    private OutputView() {
    }

    public static void printStartingCardsStatuses(Players players) {
        String names = String.join(DELIMITER, players.getNamesOfParticipants());

        System.out.println("딜러와 " + names + "에게 2장을 나누었습니다.");

        for (Participant participant : players.getPlayers()) {
            List<Card> startingCards = participant.getStartingCards();

            if (startingCards.size() == 1) {
                System.out.println(Formatter.formatSingleCardStatus(participant));
                continue;
            }
            System.out.println(Formatter.formatMultipleCardStatusWithName(participant));
        }
    }

    public static void printMoreCard() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printCardResult(ParticipantResults participantResults) {
        List<ParticipantResult> resultsOfDefender = participantResults.findResultsOfDefender();
        List<ParticipantResult> resultsOfChallenger = participantResults.findResultsOfChallenger();

        Formatter.formatDefenderCardResult(resultsOfDefender).forEach(System.out::println);
        resultsOfChallenger.stream()
                .map(Formatter::formatPlayerCardResult)
                .forEach(System.out::println);
    }

    public static void printCardResult(Participant participant) {
        System.out.println(Formatter.formatMultipleCardStatusWithName(participant));
    }

    public static void printBustedParticipantWithName(Participant participant) {
        System.out.println(participant.getName() + "는 버스트되어 더 이상 카드를 뽑을 수 없습니다!");
    }

    public static void printGameResult(ParticipantResults participantResults) {
        System.out.println("## 최종 승패");
        participantResults.findResultsOfDefender().forEach(participantResult ->
                System.out.printf("딜러: %s%n", Formatter.formatDefenderGameResult(participantResult)));
        List<ParticipantResult> resultsOfChallenger = participantResults.findResultsOfChallenger();
        System.out.println(Formatter.formatChallengerGameResult(resultsOfChallenger));
    }

}
