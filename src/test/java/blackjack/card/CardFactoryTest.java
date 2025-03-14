package blackjack.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;

class CardFactoryTest {

    @Test
    @DisplayName("카드를 생성할 수 있다")
    void canCreateCard() {
        // given
        CardSuit spade = CardSuit.SPADE;
        CardRank ace = CardRank.ACE;
        CardRank two = CardRank.TWO;

        // when
        // then
        assertThatCode(
                () -> {
                    Card spadeAce = CardFactory.create(spade, ace);
                    Card spadeTwo = CardFactory.create(spade, two);
                }
        ).doesNotThrowAnyException();
    }
}
