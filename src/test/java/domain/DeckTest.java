package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {

    @Test
    @DisplayName("덱의 처음 카드 숫자는 52개이다.")
    void getDeckSize_Return52() {
        testDeck testDeck = domain.testDeck.createStandardDeck();
        Integer realValue = domain.testDeck.getDeckSize(testDeck);
        assertEquals(52, realValue);
    }
}
