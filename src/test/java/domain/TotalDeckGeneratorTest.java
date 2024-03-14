package domain;

import domain.deck.TotalDeck;
import domain.deck.TotalDeckGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TotalDeckGeneratorTest {
    @Test
    @DisplayName("전체 덱을 만든다.")
    void generateTest() {
        TotalDeck cards = TotalDeckGenerator.generate();

        assertThat(cards.size()).isEqualTo(52);
    }
}

