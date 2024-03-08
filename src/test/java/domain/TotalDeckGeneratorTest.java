package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TotalDeckGeneratorTest {
    @Test
    @DisplayName("전체 덱을 만든다.")
    void generateTest() {
        List<Card> cards = TotalDeckGenerator.generate();

        assertThat(cards).hasSize(52);
    }
}

