package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CardTest {

    @ParameterizedTest(name = "퀸은 10으로 계산한다")
    @CsvSource({"hart, Q", "spade, K", "dia, J"})
    void a(String face, String letter) {
        Card card = new Card(face, letter);
        Assertions.assertThat(card.calculateNumber()).isEqualTo(10);
    }

}