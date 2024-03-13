package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeckGeneratorTest {
    @Test
    @DisplayName("덱의 크기는 52장이다.")
    void generateTest() {
        List<Card> cards = new DeckGenerator().generate();

        assertThat(cards).hasSize(52);
    }

    @Test
    @DisplayName("덱은 생성될때마다 셔플된다.")
    void generateIsShuffleTest() {
        DeckGenerator deckGenerator = new DeckGenerator();
        List<Card> cards1 = deckGenerator.generate();
        List<Card> cards2 = deckGenerator.generate();

        assertThat(cards1).containsExactlyInAnyOrderElementsOf(cards2)
                .isNotEqualTo(cards2);
    }
}

