package blackjack.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {

    @DisplayName("카드는 문양과 숫자를 가진다.")
    @Test
    void card() {
        //given
        final var symbol = CardSymbol.SPADE;
        final var number = CardRank.ACE;

        //when //then
        assertThatCode(() -> new Card(symbol, number))
                .doesNotThrowAnyException();
    }

    @DisplayName("52장의 카드는 캐싱되어있다.")
    @Test
    void cachingCard() {
        // given
        Card card1 = Card.of(CardSymbol.SPADE, CardRank.EIGHT);
        Card card2 = Card.of(CardSymbol.SPADE, CardRank.EIGHT);

        //when
        boolean actual1 = card1.equals(card2);
        boolean actual2 = card1 == card2;

        //then
        assertThat(actual1).isTrue();
        assertThat(actual2).isTrue();


    }
}
