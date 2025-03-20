package blackjack.domain.card;

import blackjack.fixture.CardsFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class CardsTest {

    @ParameterizedTest
    @CsvSource({
            "TWO,THREE,5",
            "ACE,TWO,13",
            "ACE,QUEEN,21",
    })
    @DisplayName("카드 숫자 합을 계산한다")
    void sumTest(CardNumber cardNumber1, CardNumber cardNumber2, int expected) {
        // when
        Cards cards = CardsFixture.cardsOf(cardNumber1, cardNumber2);

        // then
        assertThat(cards.sum()).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "ACE,JACK,true",
            "ACE,QUEEN,true",
            "ACE,NINE,false",
    })
    @DisplayName("블랙잭 여부를 확인한다")
    void isBlackjackTest(CardNumber cardNumber1, CardNumber cardNumber2, boolean expected) {
        // when
        Cards cards = CardsFixture.cardsOf(cardNumber1, cardNumber2);

        // then
        assertThat(cards.isBlackjack()).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "ACE,TWO,JACK,false",
            "JACK,QUEEN,ACE,false",
            "JACK,QUEEN,TWO,true",
    })
    @DisplayName("버스트 여부를 확인한다")
    void isBustTest(CardNumber cardNumber1, CardNumber cardNumber2, CardNumber cardNumber3, boolean expected) {
        // when
        Cards cards = CardsFixture.cardsOf(cardNumber1, cardNumber2, cardNumber3);

        // then
        assertThat(cards.isBust()).isEqualTo(expected);
    }
}
