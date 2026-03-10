package domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardsTest {

    @Test
    @DisplayName("카드를 52장 생성한다.")
    void createCards_Returns52() {
        Cards standardCards = Cards.createCards();
        int actualValue = Cards.getCardsSize(standardCards);
        assertEquals(52, actualValue);
    }

    @Test
    @DisplayName("이미 생성된 카드를 섞는다.")
    void shuffleCards_ReturnShuffledCards() {
        Cards standardCards = Cards.createCards();
        List<Card> beforeShuffle = new ArrayList<>(standardCards.getCards());
        Cards.shuffleCards(standardCards);
        List<Card> afterShuffle = standardCards.getCards();
        assertNotEquals(beforeShuffle, afterShuffle);
    }
}
