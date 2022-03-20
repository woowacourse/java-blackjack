package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.entry.Participant;
import java.text.MessageFormat;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private static final String NAME_DELIMITER = ", ";
    private static final int FIRST_DEALER_CARD = 0;

    public static void printAllCards(List<Participant> participants) {
        System.out.println(MessageFormat.format("딜러와 {0}에게 2장의 카드를 나누었습니다.", toNames(participants)));
        System.out.println();

        participants.forEach(OutputView::printParticipantsCard);
        System.out.println();
    }

    private static void printParticipantsCard(Participant participant) {
        if (participant.isDealer()) {
            printCard(participant.getName().getValue(),
                Collections.singletonList(participant.getCards().get(FIRST_DEALER_CARD)));
            return;
        }
        printCard(participant.getName().getValue(), participant.getCards());
    }

    public static void printDealerHitCount(int hitCount) {
        System.out.println();
        System.out.println(MessageFormat.format("딜러는 16이하라 {0}장의 카드를 더 받았습니다.", hitCount));
        System.out.println();
    }

    public static void printCard(String name, List<Card> cards) {
        System.out.println(MessageFormat.format("{0}카드: {1}", name, cards.stream()
            .map(card -> card.getNumber().getName() + card.getSuit().getName())
            .collect(Collectors.joining(NAME_DELIMITER))));
    }

    public static void printGameResult(Map<Participant, Double> participants) {
        printCardResult(participants);
        System.out.println();
        printProfit(participants);
    }

    private static String toNames(List<Participant> participants) {
        return participants.stream()
            .map(participant -> participant.getName().getValue())
            .collect(Collectors.joining(NAME_DELIMITER));
    }

    private static String cards(List<Card> cards) {
        return cards.stream()
            .map(card -> card.getNumber().getName() + card.getSuit().getName())
            .collect(Collectors.joining(NAME_DELIMITER));
    }

    private static void printCardResult(Map<Participant, Double> participants) {
        for (Participant participant : participants.keySet()) {
            System.out.println(MessageFormat.format("{0}카드: {1} - 결과: {2}",
                participant.getName().getValue(),
                cards(participant.getCards()),
                participant.countScore())
            );
        }
    }

    private static void printProfit(Map<Participant, Double> participants) {
        System.out.println("## 최종 수익");
        for (Participant participant : participants.keySet()) {
            System.out.println(participant.getName().getValue() + ": " + participants.get(participant).intValue());
        }
    }
}
