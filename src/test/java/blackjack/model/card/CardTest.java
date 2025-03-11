package blackjack.model.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.List;
import java.util.Set;
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

    @DisplayName("52장의 서로 다른 카드 덱을 생성한다.")
    @Test
    void createDeckTest() {
        // when
        List<Card> cards = Card.createDeck();

        // then
        assertThat(cards.size())
                .isSameAs(Set.copyOf(cards).size());
    }
}
