package blackjack.domain;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Deck {
    private static boolean lock = false;
    private static final String DECK_GENERATE_LOCK_EXCEPTION
            = "[ERROR] 덱은 이미 존재합니다.";

    private static final String FILE_PATH = "src/main/java/blackjack/domain/CardType";
    private static final String FILE_IO_EXCEPTION = "[ERROR] 파일 입력 에러";
    private static final String FILE_CONTENT_DELIMITER = " ";
    private static final int NAME_INDEX = 0;
    private static final int VALUE_INDEX = 1;
    private static final String NO_AVAILABLE_CARD_EXCEPTION = "[ERROR] 덱이 비었습니다.";

    private final List<Card> cards;
    private final List<Boolean> isExist;

    private Deck() {
        this.cards = new ArrayList<>();
        this.isExist = new ArrayList<>();
        addCardsFromFile();
    }

    private void addCardsFromFile() {
        try {
            BufferedReader bufReader = new BufferedReader(new FileReader(FILE_PATH));
            String line;
            while ((line = bufReader.readLine()) != null) {
                List<String> nameAndValue = List.of(line.split(FILE_CONTENT_DELIMITER));
                cards.add(Card.generateCard(nameAndValue.get(NAME_INDEX)
                        , Integer.parseInt(nameAndValue.get(VALUE_INDEX))));
                isExist.add(false);
            }
            Card.lock();
        } catch (Exception e) {
            throw new RuntimeException(FILE_IO_EXCEPTION);
        }
    }

    public static Deck generateDeck() {
        if (lock) {
            throw new RuntimeException(DECK_GENERATE_LOCK_EXCEPTION);
        }
        Deck deck = new Deck();
        lock = true;
        return deck;
    }

    public Card randomPick(NumberGenerator numberGenerator) {
        validateDeckIsNotEmpty();
        int cardIndex = numberGenerator.generateNumber();
        while (Boolean.TRUE.equals(isExist.get(cardIndex))) {
            cardIndex = numberGenerator.generateNumber();
        }
        isExist.set(cardIndex, true);
        return cards.get(cardIndex);
    }

    private void validateDeckIsNotEmpty() {
        if (!isExist.contains(false)) {
            throw new RuntimeException(NO_AVAILABLE_CARD_EXCEPTION);
        }
    }
}
