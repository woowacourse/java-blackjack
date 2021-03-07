package blackjack.domain.card;

import blackjack.domain.card.painting.Suit;
import blackjack.domain.card.painting.Symbol;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class CardTest {
    @DisplayName("suit와 value를 주어 Card를 생성한다")
    @ParameterizedTest
    @CsvSource({"SPADE,TWO", "CLOVER,THREE", "HEART,ACE", "DIAMOND,JACK"})
    void cardTest(Suit suit, Symbol symbol) {
        //given
        //when
        Card card = new Card(suit, symbol);

        //then
        assertThat(card.isSameSuit(suit)).isTrue();
        assertThat(card.isSameSymbol(symbol)).isTrue();
    }
}