import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


class CardsTest {

    @DisplayName("52개의 카드를 생성할 수 있다.")
    @ParameterizedTest
    @CsvSource({"A,스페이드", "2,하트", "3,다이아몬드", "J,하트", "K,클로버"})
    void createCardsTest(String value, String shape) {
        Cards cards = new Cards();
        Card card = new Card(value, shape);
        assertThat(cards.contains(card)).isTrue();
    }
}
