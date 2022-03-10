package blackjack.domain;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Card {
    private static final String FILE_PATH = "src/main/java/blackjack/domain/CardType";
    private static final String FILE_IO_EXCEPTION = "[ERROR] 파일 입력 에러";
    private static final String FILE_CONTENT_DELIMITER = " ";
    private static final String NO_AVAILABLE_CARD_EXCEPTION = "[ERROR] 더 이상 카드를 뽑을 수 없습니다.";
    private static final int NAME_INDEX = 0;
    private static final int VALUE_INDEX = 1;
    private static final List<Card> CACHE = new ArrayList<>();
    private static final List<Boolean> IS_PICKED = new ArrayList<>();

    private final String name;
    private final int number;

    static {
        try {
            File file = new File(FILE_PATH);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufReader.readLine()) != null) {
                List<String> nameAndValue = List.of(line.split(FILE_CONTENT_DELIMITER));
                CACHE.add(new Card(nameAndValue.get(NAME_INDEX), Integer.parseInt(nameAndValue.get(VALUE_INDEX))));
                IS_PICKED.add(false);
            }
        } catch (Exception e) {
            throw new RuntimeException(FILE_IO_EXCEPTION);
        }
    }

    public Card(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public static Card randomPick(NumberGenerator numberGenerator) {
        validateCardRemain();
        int cardIndex = numberGenerator.generateNumber();
        while (Boolean.TRUE.equals(IS_PICKED.get(cardIndex))) {
            cardIndex = numberGenerator.generateNumber();
        }
        IS_PICKED.set(cardIndex, true);
        return CACHE.get(cardIndex);
    }

    private static void validateCardRemain() {
        if (!IS_PICKED.contains(false)) {
            throw new RuntimeException(NO_AVAILABLE_CARD_EXCEPTION);
        }
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Card card = (Card) o;
        return Objects.equals(name, card.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
