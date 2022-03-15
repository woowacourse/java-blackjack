package blackjack.domain;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Deck {
    private static final String FILE_PATH = "src/main/java/blackjack/domain/CardType";
    private static final String FILE_IO_EXCEPTION = "[ERROR] 파일 입력 에러";
    private static final String NO_AVAILABLE_CARD_EXCEPTION = "[ERROR] 덱이 비었습니다.";
    private static final String FILE_CONTENT_DELIMITER = " ";
    private static final int NAME_INDEX = 0;
    private static final int VALUE_INDEX = 1;

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
                isExist.add(true);
            }
        } catch (Exception e) {
            throw new RuntimeException(FILE_IO_EXCEPTION);
        }
    }

    public static Deck generateDeck() {
        return new Deck();
    }

    public Card randomPick(NumberGenerator numberGenerator) {
        validateDeckIsNotEmpty();
        int cardIndex = numberGenerator.generateNumber();
        while (Boolean.FALSE.equals(isExist.get(cardIndex))) {
            cardIndex = numberGenerator.generateNumber();
        }
        isExist.set(cardIndex, false);
        return cards.get(cardIndex);
    }

    private void validateDeckIsNotEmpty() {
        if (Boolean.FALSE.equals(isExist.contains(true))) {
            throw new RuntimeException(NO_AVAILABLE_CARD_EXCEPTION);
        }
    }
}
