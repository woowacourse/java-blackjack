package blackjack.view;

import java.util.List;

public class ResultView {
    private static final String FORMAT_MESSAGE_DECK_INITIALIZED = "딜러와 %s에게 2장의 카드를 나누었습니다.%n";
    private static final String FORMAT_DECK_INITIALIZED = "%s : %s%n";

    public static final String DELIMITER_NAME = ", ";

    public void printDeckInitialized(List<String> names) {
        String joinedNames = String.join(DELIMITER_NAME, names);
        System.out.printf(FORMAT_MESSAGE_DECK_INITIALIZED, joinedNames);
    }

    public void printInitializedDecks(List<String> names, List<List<String>> decks) {
        for (int index = 0; index < names.size(); index++) {
            printInitializedDeck(names.get(index), decks.get(index));
        }
    }

    private void printInitializedDeck(String name, List<String> deck) {
        //TODO : joinning 겹치는 로직 메서드 분리
        String joinedCards = String.join(DELIMITER_NAME, deck);
        System.out.printf(FORMAT_DECK_INITIALIZED, name, joinedCards);
    }
}
