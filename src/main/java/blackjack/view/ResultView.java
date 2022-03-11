package blackjack.view;

import java.util.List;

public class ResultView {
    private static final String FORMAT_MESSAGE_DECK_INITIALIZED = "%n딜러와 %s에게 2장의 카드를 나누었습니다.%n";
    private static final String FORMAT_DECK_INITIALIZED = "%s : %s%n";
    private static final String FORMAT_MESSAGE_BUST = "%s의 점수 합이 21을 넘어, 다음 참가자로 넘어갑니다.%n";

    public static final String DELIMITER_NAME = ", ";

    public void printDeckInitialized(List<String> names) {
        String joinedNames = String.join(DELIMITER_NAME, names);
        System.out.printf(FORMAT_MESSAGE_DECK_INITIALIZED, joinedNames);
    }

    public void printInitializedDecks(List<String> names, List<List<String>> decks) {
        for (int index = 0; index < names.size(); index++) {
            printDeck(names.get(index), decks.get(index));
        }
    }

    public void printDeck(String name, List<String> deck) {
        //TODO : joinning 겹치는 로직 메서드 분리
        String joinedCards = String.join(DELIMITER_NAME, deck);
        System.out.printf(FORMAT_DECK_INITIALIZED, name, joinedCards);
    }

    public void printBustMessage(String name) {
        System.out.printf(FORMAT_MESSAGE_BUST, name);
    }
}
