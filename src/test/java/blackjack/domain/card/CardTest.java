package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class CardTest {
    @DisplayName("suit와 value를 주어 Card를 생성한다")
    @ParameterizedTest
    @CsvSource({"SPADE,TWO", "CLOVER,THREE", "HEART,ACE", "DIAMOND,JACK"})
    void cardTest(Suit suit, Value value) {
        //given
        //when
        Card card = new Card(suit, value);

        //then
        assertThat(card.getSuit()).isEqualTo(suit);
        assertThat(card.getValue()).isEqualTo(value);
    }
}