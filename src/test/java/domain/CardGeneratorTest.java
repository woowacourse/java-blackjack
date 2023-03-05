package domain;

import static org.assertj.core.api.Assertions.*;

import domain.strategy.NoShuffleCardsStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Queue;

public class CardGeneratorTest {
    private CardGenerator cardGenerator;
    private Queue<Card> cards;
    @BeforeEach
    void beforeEach(){
        cardGenerator = new CardGenerator();
        cards = cardGenerator.generate(new NoShuffleCardsStrategy());
    }

    @Test
    @DisplayName("52개의 카드를 생성한다.")
    void createCard() {
        assertThat(cards.size()).isEqualTo(52);
    }

}
