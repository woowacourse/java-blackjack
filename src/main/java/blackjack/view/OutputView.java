package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.Cards;
import blackjack.dto.Participants;
import blackjack.domain.Player;

import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class OutputView {

    private OutputView() {
    }

    public static void printCards(Player player) {
        String cards = player.getUnmodifiableCards().stream()
            .map(Card::getCardName)
            .collect(Collectors.joining(", "));
        System.out.println(player.getName() + "카드: " + cards);
    }

    public static void printParticipantsCards(Participants participants) {
        System.out.printf("\n딜러와 %s에게 2장의 나누었습니다.\n", participants.names());
        for (Entry<String, Cards> value : participants.cards().entrySet()) {
            System.out.println(value.getKey() + cardsToString(value.getValue()));
        }
    }

    private static String cardsToString(Cards cards) {
        return cards.getUnmodifiableList().stream()
            .map(Card::getCardName)
            .collect(Collectors.joining(", ", "카드: ", ""));
    }

    public static void printDealerGetCard() {
        System.out.println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.\n");
    }

    public static void printResult(Participants participants) {
        printParticipantsResults(participants);
        printWinOrLose(participants.finalResult());
    }

    private static void printParticipantsResults(Participants participants) {
        for (Entry<String, Cards> value : participants.cards().entrySet()) {
            System.out.println(value.getKey() + cardsToString(value.getValue())
                    + " - 결과: " + value.getValue().sumCardsForResult());
        }
    }

    private static void printWinOrLose(Map<String, String> results) {
        System.out.println("\n## 최종 승패");
        for (Map.Entry<String, String> entry : results.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }


}
