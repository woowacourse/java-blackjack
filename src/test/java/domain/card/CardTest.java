package domain.card;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class CardTest {

    @DisplayName("카드 번호가 0~51 범위를 벗어나면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {-1, 52, -100, 100})
    void throwExceptionWhenCardNumberIsOutOfRange(int cardNumber) {
        assertThatThrownBy(() -> new Card(cardNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("카드는 0~51 범위여야 합니다.");
    }

    @DisplayName("0~51 사이의 번호로 카드를 생성한다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 25, 51})
    void createCard(int cardNumber) {
        Card card = new Card(cardNumber);
        assertThat(card).isNotNull();
    }

    @DisplayName("카드 이름을 정상적으로 반환한다.")
    @ParameterizedTest
    @CsvSource({
            "0, 스페이드A", "1, 스페이드2", "12, 스페이드K",
            "13, 하트A", "25, 하트K",
            "26, 다이아몬드A",
            "39, 클로버A", "51, 클로버K"
    })
    void getCardName(int cardNumber, String expectedName) {
        Card card = new Card(cardNumber);
        assertThat(card.getCardName()).isEqualTo(expectedName);
    }

    @DisplayName("카드의 점수를 정상적으로 계산하여 반환한다.")
    @ParameterizedTest
    @CsvSource({
            "0, 11",
            "1, 2",
            "8, 9",
            "9, 10",
            "10, 10",
            "11, 10",
            "12, 10"
    })
    void getScore(int cardNumber, int expectedScore) {
        Card card = new Card(cardNumber);
        assertThat(card.getScore()).isEqualTo(expectedScore);
    }

    @DisplayName("카드가 Ace인지 정상적으로 판별한다.")
    @ParameterizedTest
    @CsvSource({
            "0, true", "13, true", "26, true", "39, true",
            "1, false", "10, false"
    })
    void isAce(int cardNumber, boolean expected) {
        Card card = new Card(cardNumber);
        assertThat(card.isAce()).isEqualTo(expected);
    }
}
