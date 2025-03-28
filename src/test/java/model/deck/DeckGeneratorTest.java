package model.deck;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckGeneratorTest {

    @DisplayName("초기의 52장의 카드를 생성한다.")
    @Test
    void test1() {
        assertThat(new DeckGenerator().getInitializedDeck())
                .hasSize(52)
                .doesNotHaveDuplicates();
    }

}
