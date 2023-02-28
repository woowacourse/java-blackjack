package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CardGeneratorTest {
    private CardGenerator cardGenerator;
    private List<Card> cards;
    @BeforeEach
    void beforeEach(){
        cardGenerator = new CardGenerator();
        cards = cardGenerator.generate();
    }


    @Test
    @DisplayName("52개의 카드를 생성한다.")
    void createCard() {
        assertThat(cards.size()).isEqualTo(52);
    }

    @Test
    @DisplayName("52개의 카드가 완전히 생성되었는지 확인한다")
    void checkCardsTest() {
        assertThat(cards.get(0)).usingRecursiveComparison().isEqualTo(new Card(CardNumber.ACE,CardPattern.SPADE));
        assertThat(cards.get(51)).usingRecursiveComparison().isEqualTo(new Card(CardNumber.KING,CardPattern.CLOVER));
    }
}
