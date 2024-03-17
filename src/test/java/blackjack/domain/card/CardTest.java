package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import blackjack.domain.fixture.CardFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {

    @DisplayName("카드를 생성한다")
    @Test
    void create() {
        assertThatCode(() -> new Card(CardSuit.CLOVER, CardNumber.JACK))
                .doesNotThrowAnyException();
    }

    @DisplayName("카드가 가진 값을 반환한다")
    @Test
    void getNumber() {
        Card card = new Card(CardSuit.CLOVER, CardNumber.JACK);

        assertThat(card.getNumber()).isEqualTo(CardNumber.JACK);
    }

    @DisplayName("카드가 가진 상징을 반환한다")
    @Test
    void getSuit() {
        Card card = new Card(CardSuit.CLOVER, CardNumber.JACK);

        assertThat(card.getSymbol()).isEqualTo(CardSuit.CLOVER);
    }

    @DisplayName("카드가 Ace 라면 true 를 반환한다")
    @Test
    void testIsAce1() {
        assertThat(CardFixture.cloverAce().isAce()).isTrue();
    }

    @DisplayName("카드가 Ace 가 아니라면 false 를 반환한다")
    @Test
    void testIsAce2() {
        assertThat(CardFixture.heartJack().isAce()).isFalse();
    }
}
