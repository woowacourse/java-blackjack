package blackjack.model.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("카드 테스트")
class CardTest {

    @DisplayName("카드는 모양과 숫자로 구성된다")
    @ParameterizedTest
    @CsvSource(value = {
            "HEARTS, ACE",
            "SPADES, TWO",
            "CLUBS, TEN",
            "DIAMONDS, JACK",
    })
    void createCardTest(Suit suit, CardValue cardValue) {
        // when, then
        assertThatCode(() -> new Card(suit, cardValue))
                .doesNotThrowAnyException();
    }

    @DisplayName("같은 모양과 숫자를 가진 카드는 같아야 한다.")
    @Test
    void equalsTest() {
        // given
        Card card = new Card(Suit.SPADES, CardValue.ACE);

        // when, then
        assertThat(card)
                .isEqualTo(new Card(Suit.SPADES, CardValue.ACE));
    }

    @DisplayName("카드의 모양과 숫자를 알 수 있다.")
    @Test
    void getDisplayLabelTest() {
        // given
        Card card = new Card(Suit.SPADES, CardValue.ACE);

        // when
        String displayLabel = card.getDisplayLabel();

        // then
        assertThat(displayLabel)
                .isEqualTo("A스페이드");
    }
}
