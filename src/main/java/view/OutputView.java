package view;

import domain.Card;
import domain.Game;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class OutputView {
    private static final String DELIMITER = ", ";

    public void displayInitialDeal(Game game) {
        Map<String, List<Card>> playerNameAndCards = game.getPlayerNameAndCards();
        System.out.printf("%n딜러와 %s에게 2장을 나누었습니다.%n", String.join(DELIMITER, playerNameAndCards.keySet()));
        System.out.printf("딜러카드: %s%n", game.getDealerCards().getFirst().getNotation());
        for (Entry<String, List<Card>> nameAndCardsEntry : playerNameAndCards.entrySet()) {
            displayPlayerAndCards(nameAndCardsEntry);
        }
    }

    private void displayPlayerAndCards(Entry<String, List<Card>> nameAndCardsEntry) {
        String name = nameAndCardsEntry.getKey();
        List<String> cardNotations = nameAndCardsEntry.getValue().stream()
                .map(Card::getNotation)
                .toList();
        System.out.printf("%s카드: %s%n", name, String.join(DELIMITER, cardNotations));
    }
}
