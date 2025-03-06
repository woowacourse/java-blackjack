package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CardsTest {

    @ParameterizedTest
    @CsvSource({
        "TWO,THREE,5",
        "ACE,TWO,13",
        "ACE,QUEEN,21",
    })
    @DisplayName("카드 숫자 합을 계산한다")
    void sumTest(CardNumber cardNumber1, CardNumber cardNumber2, int expected) {
        Cards cards = new Cards();
        cards.add(new Card(CardType.CLOVER, cardNumber1));
        cards.add(new Card(CardType.CLOVER, cardNumber2));

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
        Cards cards = new Cards();
        cards.add(new Card(CardType.CLOVER, cardNumber1));
        cards.add(new Card(CardType.CLOVER, cardNumber2));

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
        Cards cards = new Cards();
        cards.add(new Card(CardType.CLOVER, cardNumber1));
        cards.add(new Card(CardType.CLOVER, cardNumber2));
        cards.add(new Card(CardType.CLOVER, cardNumber3));

        assertThat(cards.isBust()).isEqualTo(expected);
    }
}
