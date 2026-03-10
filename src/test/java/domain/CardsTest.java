package domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardsTest {

    @Test
    @DisplayName("카드를 52장 생성한다.")
    void createCards_Returns52() {
        List<Card> standCards = Cards.createCards();
        int actualValue = standCards.size();
        assertEquals(52, actualValue);
    }

    @Test
    @DisplayName("이미 생성된 카드를 섞는다.")
    void shuffleCards_ReturnShuffledCards() {
        List<Card> standCards = Cards.createCards();
        List<Card> shuffledCards = Cards.shuffleCards(standCards);
        assertNotEquals(standCards, shuffledCards);

    }
}
