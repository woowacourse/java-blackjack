package blackjack.view;

import blackjack.domain.Outcome;
import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.dto.Results;
import blackjack.domain.gamer.Player;

import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class OutputView {

    private OutputView() {
    }

    public static void printCards(Player player) {
        final String cards = player.getUnmodifiableCards().stream()
            .map(Card::getCardName)
            .collect(Collectors.joining(", "));

        System.out.println(player.getName() + "카드: " + cards);
    }

    public static void printParticipantsCards(Results results) {
        final String joinedNames = results.names().stream()
            .collect(Collectors.joining(", "));

        System.out.printf("\n딜러와 %s에게 2장의 나누었습니다.\n", joinedNames);

        for (Entry<String, Cards> value : results.cards().entrySet()) {
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

    public static void printResult(Results results) {
        printParticipantsResults(results);
        printWinOrLose(results);
    }

    private static void printParticipantsResults(Results results) {
        for (Entry<String, Cards> value : results.cards().entrySet()) {
            System.out.println(value.getKey() + cardsToString(value.getValue())
                + " - 결과: " + value.getValue().sumCardsForResult());
        }
    }

    private static void printWinOrLose(Results results) {
        System.out.println("\n## 최종 승패");

        printDealerOutcome(results.dealerName(), results.getDealerFinalOutcome());
        printPlayersOutcome(results.getPlayersFinalOutcome());
    }

    private static void printPlayersOutcome(Map<String, String> playersFinalOutcome) {
        for (Map.Entry<String, String> entry : playersFinalOutcome.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    private static void printDealerOutcome(String dealer, Map<Outcome, Integer> dealerInfo) {
        System.out.println(dealer + ": "
            + dealerInfo.get(Outcome.WIN) + "승"
            + dealerInfo.get(Outcome.LOSE) + "패"
            + dealerInfo.get(Outcome.DRAW) + "무"
        );

    }


}
