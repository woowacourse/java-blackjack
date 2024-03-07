package domain;

import domain.card.Card;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TotalDeckGeneratorTest {
    @Test
    @DisplayName("전체 덱을 만든다.")
    void generateTest() {
        TotalDeckGenerator totalDeckGenerator = new TotalDeckGenerator();

        List<Card> cards = totalDeckGenerator.generate();

        assertThat(cards).hasSize(52);
    }
}

