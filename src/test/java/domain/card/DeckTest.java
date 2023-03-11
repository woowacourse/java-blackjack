package domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DeckTest {

    @ParameterizedTest
    @CsvSource(value = {"A하트, 1", "3하트, 3", "5다이아몬드, 5", "K스페이드, 10", "Q클로버, 10", "J하트, 10"})
    @DisplayName("각 카드에 해당하는 숫자를 정상적으로 추출한다.")
    void shouldSuccessExtractCardNumber(String card, int cardNumber) {
        Deck deck = new Deck();
        assertThat(deck.extractCardNumber(card)).isEqualTo(cardNumber);
    }
}
