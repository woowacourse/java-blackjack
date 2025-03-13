package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("카드 테스트")
class CardTest {

    @Test
    @DisplayName("카드는 숫자와 모양을 가진다")
    void cardHasNumberAndShape() {
        Card card = new Card(CardNumber.EIGHT, CardShape.CLOVER);

        assertAll(
                () -> assertThat(card.getValue()).isEqualTo(8),
                () -> assertThat(card.getShape()).isEqualTo(CardShape.CLOVER)
        );
    }

    @ParameterizedTest
    @CsvSource({
            "ACE, CLOVER, true",
            "TWO, CLOVER, false",
    })
    @DisplayName("카드가 에이스인지 판단한다")
    void shouldDetermineIfCardIsAce(CardNumber cardNumber, CardShape cardShape, boolean excepted) {
        Card card = new Card(cardNumber, cardShape);

        boolean result = card.isAce();

        assertThat(result).isEqualTo(excepted);
    }

    @ParameterizedTest
    @CsvSource({
            "ACE, CLOVER, 1",
            "TWO, CLOVER, 2",
    })
    @DisplayName("카드의 값을 반환한다")
    void getCardValue(CardNumber cardNumber, CardShape cardShape, int excepted) {
        Card card = new Card(cardNumber, cardShape);

        int result = card.getValue();

        assertThat(result).isEqualTo(excepted);
    }

    @ParameterizedTest
    @CsvSource({
            "ACE, CLOVER, ACE, CLOVER, TRUE",
            "ACE, CLOVER, ACE, DIAMOND, FALSE",
            "ACE, CLOVER, TWO, CLOVER, FALSE"
    })
    @DisplayName("동일한 카드인지 판단한다")
    void shouldDetermineIfCardsAreEqual(CardNumber cardNumber, CardShape cardShape, CardNumber otherCardNumber, CardShape otherCardShape, boolean excepted) {
        Card card = new Card(cardNumber, cardShape);
        Card otherCard = new Card(otherCardNumber, otherCardShape);

        boolean result = card.equals(otherCard);

        assertThat(result).isEqualTo(excepted);
    }
}
