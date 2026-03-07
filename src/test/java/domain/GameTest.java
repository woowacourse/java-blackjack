package domain;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameTest {
    @Test
    @DisplayName("생성 잘 한다")
    void ready_good() {
        //given
        List<String> testPlayerNames = List.of("pobi", "terry", "rati", "gump");
        CardCreationStrategy totalCardCreationStrategy = this::createSampleCards;

        //when, then
        assertDoesNotThrow(
                () -> Game.ready(testPlayerNames, totalCardCreationStrategy)
        );
    }

    private List<Card> createSampleCards() {
        CardShape[] shapes = CardShape.values();
        CardContents[] contents = CardContents.values();

        List<Card> sampleCards = new ArrayList<>();
        for (CardShape cardShape : shapes) {
            for (CardContents content : contents) {
                sampleCards.add(new Card(cardShape, content));
            }
        }

        return sampleCards;
    }
}