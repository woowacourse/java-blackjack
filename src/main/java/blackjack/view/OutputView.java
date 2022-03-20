package blackjack.view;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import blackjack.domain.card.Card;
import blackjack.domain.player.Participant;
import blackjack.domain.player.Participants;

public class OutputView {

    public static void printParticipantsCards(Participants participants) {
        System.out.printf("%n%s와 %s에게 2장을 나누었습니다.%n",
            participants.findDealer()
                .getName(),
            toParticipantsName(participants));
        for (Participant participant : participants.getParticipants()) {
            printCards(participant);
        }
    }

    private static String toParticipantsName(Participants participants) {
        return participants.getPlayers()
            .stream()
            .map(Participant::getName)
            .collect(Collectors.joining(", "));
    }

    public static void printCards(Participant participant) {
        System.out.printf("%s: %s%n", participant.getName(),
            toHoldCardsUi(participant.openCards()));
    }

    private static void printPlayerResult(Participant participant) {
        System.out.printf("%s: %s - 결과: %d%n",
            participant.getName(),
            toHoldCardsUi(participant.getHoldCards()),
            participant.getTotalNumber());
    }

    private static String toHoldCardsUi(List<Card> cards) {
        return cards.stream()
            .map(card -> card.getCardNumber().getName() + card.getPatternName())
            .collect(Collectors.joining(", "));
    }

    public static void printParticipantsResult(Participants participants) {
        for (Participant participant : participants.getParticipants()) {
            printPlayerResult(participant);
        }
    }

    public static void printDealerDrawMessage() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printScoreResult(Map<Participant, Integer> playersProfit) {
        System.out.println(System.lineSeparator() + "## 최종 수익");
        playersProfit.forEach((player, profit) -> System.out.printf("%s : %d%n", player.getName(), profit));
    }

    public static void printException(Exception exception) {
        System.out.println(exception.getMessage());
    }
}
